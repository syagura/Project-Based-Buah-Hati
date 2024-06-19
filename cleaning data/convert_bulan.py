import pandas as pd
import re

# Fungsi untuk mengonversi usia ke dalam bulan
def convert_age_to_months(age_str):
    years = 0
    months = 0
    days = 0
    
    # Regex untuk mengekstrak angka dari string
    match_years = re.search(r'(\d+)\s*Tahun', age_str)
    match_months = re.search(r'(\d+)\s*Bulan', age_str)
    match_days = re.search(r'(\d+)\s*Hari', age_str)
    
    if match_years:
        years = int(match_years.group(1))
    if match_months:
        months = int(match_months.group(1))
    if match_days:
        days = int(match_days.group(1))
    
    # Konversi ke bulan
    total_months = years * 12 + months + round(days / 30)
    return total_months

# Baca file Excel
df = pd.read_excel('dataset bangkit .xlsx')

# Asumsikan kolom usia saat ini adalah 'Usia'
df['Usia_dalam_Bulan'] = df['Usia Saat Ukur'].apply(convert_age_to_months)

# Tulis hasil ke file Excel baru
df.to_excel('data_usia_dalam_bulan.xlsx', index=False)

print("Konversi selesai. Data disimpan di 'data_usia_dalam_bulan.xlsx'.")
