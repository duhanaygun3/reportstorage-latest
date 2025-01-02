// src/app/store.js
import { configureStore } from '@reduxjs/toolkit';
import { apiSlice } from './apiSlice';

export const store = configureStore({
    reducer: {
          // Üretilen reducer'ı özel bir ana düzey kesite (slice) olarak ekleyin
        [apiSlice.reducerPath]: apiSlice.reducer, // API reducer'ını store'a eklemek
    },
    // api middleware'i eklemek, önbellekleme, geçersiz kılma, anketleme ve
 // `rtk-query`'nin diğer faydalı özelliklerini etkinleştirir.
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(apiSlice.middleware), 
});
