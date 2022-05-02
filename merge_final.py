import csv
import pandas as pd
import numpy as np

# 1. Group current dataset by sex (male or female)

# this new df should give a similar result to: 
# Sex    HeartDisease
# Female    275.6
# Male      125

# dataframe from dataset #1 
dataframe_1 = pd.read_csv ('heart.csv')

dataframe_1.head()  #debugging

# Aggregate dataframe 1

df1_aggregated = dataframe_1.groupby("Sex")
df1_aggregated.first()  #debugging

# 2. Find second dataset to merge with 
# https://www.kaggle.com/datasets/nareshbhat/health-care-data-set-on-heart-attack-possibility 

# 3. Prepare second dataset for merge
dataframe_2 = pd.read_csv ('heart2.csv')
dataframe_2.head()

# Group second dataset by sex (f/m) as well

# resting blood pressure, should give a similar result to:
# sex  trestbps
# 0      12
# 1      23

df2_aggregated = dataframe_2.groupby("sex")
df1_aggregated.first()  #debugging

# count aggregated dataframes

df1_final = df1_aggregated.count()
df1_final.reset_index(inplace=True)

print(df1_final)

df2_final = df2_aggregated.count()
print(df2_final)

df2_final.reset_index(inplace=True)
df2_final.dtypes

# re-name values in column, 0: Female, 1: Male

# sex       trestbps
# Female      12
# Male        23


df2_final["sex"].replace({0: "Female", 1: "Male"}, inplace=True)

df2_final.head()

# 3. Checking types

type(df1_final)  # pandas.core.groupby.generic.DataFrameGroupBy
type(df2_final) # pandas.core.groupby.generic.DataFrameGroupBy

# 3. Merge both datasets 

merged_df = pd.concat([df1_final.set_index('Sex'),df2_final.set_index('sex')], axis=1, join='inner')

merged_df.head()
