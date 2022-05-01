# 1. Group current dataset by sex (male or female)

# this new df should give a similar result to: 
# Sex    HeartDisease
# Female    275.6
# Male      125

df1_aggregated = df.groupby("Sex")["HeartDisease"].mean()

# 2. Find second dataset to merge with 
# https://www.kaggle.com/datasets/nareshbhat/health-care-data-set-on-heart-attack-possibility 

# 3. Prepare second dataset for merge
dataframe_2 = pd.read_csv ('heart2.csv')

# resting blood pressure, should give a similar result to:
# sex  trestbps
# 0      12
# 1      23

df2_aggregated = dataframe_2.groupby("sex")["trestbps"].mean()

# re-name values in column, 0: Female, 1: Male
df2_aggregated['Sex'] = df2_aggregated['Sex'].str.replace('0', 'Female')
df2_aggregated['Sex'] = df2_aggregated['Sex'].str.replace('1', 'Male')

# sex       trestbps
# Female      12
# Male        23

# 3. Merge both datasets 
merged_df = pd.concat([df1_aggregated.set_index('Sex'),df2_aggregated.set_index('sex')], axis=1, join='inner')

merged_df.head()
