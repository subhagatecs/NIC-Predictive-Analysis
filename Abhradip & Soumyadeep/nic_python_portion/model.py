from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import Column, Integer, String, DateTime
from sqlalchemy.types import JSON
import datetime
from pytz import timezone

db = SQLAlchemy()

class predictiveAnalysisResult(db.Model):
    __tablename__ = 'predictive_analysis_result'
    sl_no = Column(Integer, primary_key=True, autoincrement=True)
    request_key = Column(String(255), nullable=False)
    result_set = Column(JSON)
    no_of_time_accessed = Column(Integer, nullable=False, default=0)
    last_accessed_on = Column(DateTime, default=datetime.datetime.now(timezone('Asia/Kolkata')))
    last_accessed_from = Column(String(20))
    is_block = Column(Integer, nullable=False, default=0)