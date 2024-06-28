from sqlalchemy import create_engine, MetaData, Table, select
from flask import Flask, jsonify

app = Flask(__name__)

# Function to create a database connection
def create_connection(user_name, password, host_name, port, db_name):
    engine = create_engine(f'postgresql+psycopg2://{user_name}:{password}@{host_name}:{port}/{db_name}')
    return engine

# Function to fetch data from PostgreSQL
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

# Replace with your actual database credentials
db_username = 'postgres'
db_password = 'agartala'
db_hostname = 'localhost'
port = 5432
db_name = 'NIC_PREDICTIVE_ANALYSIS'
schema_name = 'analysis_data_test'
table_name = 'mas_predictive_analysis_test'

# API endpoint to fetch data
@app.route('/get_data', methods=['GET'])

def get_data():
    try:
        # Create database connection
        engine = create_connection(db_username, db_password, db_hostname, port, db_name)
        if engine:
            # Fetch data from database
            data = fetch_data_from_database(engine, schema_name, table_name)
            if data:
                # Convert data to JSON format
                data_json = [{'date': row[0], 'sale': row[1]} for row in data]  # Replace with your column names
                return jsonify(data_json)
            else:
                return jsonify({'error': 'Failed to fetch data'}), 500
        else:
            return jsonify({'error': 'Database connection failed'}), 500
    
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True)
