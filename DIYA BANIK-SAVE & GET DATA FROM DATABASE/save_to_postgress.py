from flask import Flask, jsonify
import pandas as pd
from sqlalchemy import create_engine, MetaData, Table, select

app = Flask(__name__)

# Function to create a database connection
def create_connection(user_name, password, host_name, port, db_name):
    engine = create_engine(f'postgresql+psycopg2://{user_name}:{password}@{host_name}:{port}/{db_name}')
    return engine

# Function to load data from CSV
def load_data(filename):
    try:
        df = pd.read_csv(filename)
        print(f'Loaded {len(df)} rows from {filename}')
        return df
    except Exception as e:
        print(f'Error loading data from CSV: {e}')
        return None

# Function to save data to database
def save_to_database(data, schema_name, table_name, engine):
    try:
        data.to_sql(table_name, engine, schema=schema_name, if_exists='replace', index=False)
        print(f'Data saved to database table {schema_name}.{table_name} successfully.')
    except Exception as e:
        print(f'Error saving data to database: {e}')

# Function to fetch all data from PostgreSQL
def fetch_data_from_database(engine, schema_name, table_name):
    try:
        metadata = MetaData()
        metadata.reflect(bind=engine, schema=schema_name, only=[table_name])  # Reflect table structure
        table = metadata.tables[f'{schema_name}.{table_name}']
        stmt = select(table)  # Construct select statement
        with engine.connect() as connection:
            result = connection.execute(stmt)
            data = result.fetchall()  # Fetch all rows from the result
        return data
    except Exception as e:
        print(f'Error fetching data from database: {e}')
        return None

# API endpoint to fetch data
@app.route('/get_data', methods=['GET'])
def get_data():
    try:
        # Replace with your actual database credentials
        db_username = 'postgres'
        db_password = 'agartala'
        db_hostname = 'localhost'
        port = 5432
        db_name = 'NIC_PREDICTIVE_ANALYSIS'
        schema_name = 'analysis_data_test'
        table_name = 'mas_predictive_analysis_test'

        engine = create_connection(db_username, db_password, db_hostname, port, db_name)
        if engine:
            data = fetch_data_from_database(engine, schema_name, table_name)
            if data:
                # Prepare data in JSON format
                formatted_data = [{'date': row[0], 'value': float(row[1])} for row in data]
                return jsonify(formatted_data)
            else:
                return jsonify({'error': 'Failed to fetch data'}), 500
        else:
            return jsonify({'error': 'Database connection failed'}), 500
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == "__main__":
    # Example usage to load data into database from CSV
    try:
        db_username = 'postgres'
        db_password = 'agartala'
        db_hostname = 'localhost'
        port = 5432
        db_name = 'NIC_PREDICTIVE_ANALYSIS'
        schema_name = 'analysis_data_test'
        table_name = 'mas_predictive_analysis_test'

        df = load_data('Forecasted_Sales.csv')
        if df is not None:
            engine = create_connection(db_username, db_password, db_hostname, port, db_name)
            if engine:
                save_to_database(df, schema_name, table_name, engine)
    except Exception as e:
        print(f'An error occurred: {e}')

    # Run the Flask app
    app.run(debug=True)
