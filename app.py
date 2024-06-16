from flask import Flask, jsonify
import pandas as pd
from statsmodels.tsa.arima.model import ARIMA
import json
import matplotlib.pyplot as plt
import seaborn as sns

app = Flask(__name__)
url = './sales_records.json'

# Function to fetch data from a local JSON file
def fetch_data(url):
    with open(url, 'r') as file:
        data = json.load(file)
    return data

def save_to_csv(data, filename):
    df = pd.DataFrame(data)
    df.to_csv(filename, index=False)
    print(f"Data saved to {filename}")

def load_data(filename):
    df = pd.read_csv(filename)
    return df

def preprocess_data(df):
    df['timestamp'] = pd.to_datetime('2023-01-01 ' + df['timestamp'])  # Add a dummy date to the time
    df.set_index('timestamp', inplace=True)
    df.sort_index(inplace=True)  # Ensure the index is sorted
    return df

def build_arima_model(df):
    model = ARIMA(df['quantity_sold'], order=(5, 1, 0))
    model_fit = model.fit()
    print("ARIMA model fitted")
    return model_fit

def forecast_sales(model, steps):
    forecast = model.forecast(steps=steps)
    print("Sales forecasted")
    return forecast

@app.route('/api/forecast', methods=['GET'])
def get_forecast():
    try:
        data = fetch_data(url)  # Change the URL to file path
        save_to_csv(data, 'sample.csv')
        df = load_data('sample.csv')
        df = preprocess_data(df)
        
        item_sales = df.groupby('item_id')['quantity_sold'].sum()
        most_sold_item_id = item_sales.idxmax()
        print(f"Most sold item_id: {most_sold_item_id}")
        
        df_item = df[df['item_id'] == most_sold_item_id]
        model = build_arima_model(df_item)
        
        forecast = forecast_sales(model, steps=5*12)  # Assuming monthly sales data, 5 years = 5*12 months
        
        sns.set(style="whitegrid")
        fig = plt.figure(figsize=(14, 7))
        plt.plot(df_item.index, df_item['quantity_sold'], label='Original Sales', color='blue', linestyle='-', linewidth=2)
        plt.plot(pd.date_range(start=df_item.index[-1], periods=5*12, freq='M'), forecast, label='Forecasted Sales', color='red', linestyle='--', linewidth=2)
        plt.title(f'Sales Forecast for item_id {most_sold_item_id}', fontsize=16)
        plt.xlabel('Date', fontsize=14)
        plt.ylabel('Quantity Sold', fontsize=14)
        plt.grid(True, which='both', linestyle='--', linewidth=0.5)
        plt.legend(fontsize=12)
        plt.tight_layout()
        
        plt.show()

        forecast_data = {'forecasted_sales': forecast.tolist()}
        return jsonify(forecast_data)
    except Exception as e:
        return jsonify({'error': f"An error occurred: {e}"}), 500

@app.route('/', methods=['GET'])
def index():
    return "Welcome to my Flask API! navigate to http://127.0.0.1:5000/api/forecast"


if __name__ == '__main__':
    app.run(debug=True)
