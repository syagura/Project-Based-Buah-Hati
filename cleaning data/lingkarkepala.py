import numpy as np
import pandas as pd

# Rentang lingkar kepala berdasarkan umur dan jenis kelamin
ranges = {
    'L': {
        (0, 3): {'Makrosefali': (40.6, 47), 'Normal': (34.5, 40.5), 'Mikrosefali': (28, 34.4)},
        (4, 6): {'Makrosefali': (44, 49), 'Normal': (40.5, 43), 'Mikrosefali': (31, 39)},
        (7, 12): {'Makrosefali': (47, 54), 'Normal': (43, 46), 'Mikrosefali': (36, 42)},
        (13, 36): {'Makrosefali': (50, 57), 'Normal': (46, 49), 'Mikrosefali': (39, 45)},
        (36, 60): {'Makrosefali': (52, 60), 'Normal': (49, 51), 'Mikrosefali': (41, 48)},
    },
    'P': {
        (0, 3): {'Makrosefali': (40, 47), 'Normal': (34, 39.5), 'Mikrosefali': (27, 33)},
        (4, 6): {'Makrosefali': (43, 50), 'Normal': (39.5, 42), 'Mikrosefali': (32, 38)},
        (7, 12): {'Makrosefali': (46, 52), 'Normal': (42, 45), 'Mikrosefali': (35, 41)},
        (13, 36): {'Makrosefali': (49, 55), 'Normal': (45, 48.5), 'Mikrosefali': (37, 44)},
        (36, 60): {'Makrosefali': (52, 58), 'Normal': (48.5, 51), 'Mikrosefali': (40, 47)},
    }
}

# Fungsi untuk mendapatkan rentang lingkar kepala berdasarkan umur dan jenis kelamin
def get_head_circumference_range(gender, age):
    for age_range, hc_ranges in ranges[gender].items():
        if age_range[0] <= age <= age_range[1]:
            return hc_ranges
    return None

# Fungsi untuk menghasilkan lingkar kepala acak dan kategorinya
def generate_head_circumference_and_label(gender, age):
    hc_ranges = get_head_circumference_range(gender, age)
    if not hc_ranges:
        return np.nan, np.nan
    
    # Tentukan kategori secara acak dengan probabilitas yang disesuaikan
    category = np.random.choice(['Makrosefali', 'Normal', 'Mikrosefali'], p=[0.1, 0.8, 0.1])
    range_min, range_max = hc_ranges[category]
    head_circumference = np.random.uniform(range_min, range_max)
    return head_circumference, category

# Baca file Excel
df = pd.read_excel('dataset bangkit.xlsx')

# Asumsikan kolom jenis kelamin dan umur ada dengan nama 'Jenis Kelamin' dan 'Umur (bulan)'
df[['Lingkar Kepala (cm)', 'Label(LK/Umur)']] = df.apply(lambda row: pd.Series(generate_head_circumference_and_label(row['JK'], row['Usia(Bulan)'])), axis=1)

# Tulis hasil ke file Excel baru
df.to_excel('data_dengan_lingkar_kepala_dan_label.xlsx', index=False)

print("Data acak lingkar kepala beserta label telah ditambahkan dan disimpan di 'data_dengan_lingkar_kepala_dan_label.xlsx'.")
