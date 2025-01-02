// src/features/api/apiSlice.js
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import AddPatient from './AddPatient';

export const apiSlice = createApi({
    reducerPath: 'api', // Store'daki slice ismi
    baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8080/api/' }), // API'nin temel adresi
    endpoints: (builder) => ({
        getReportById: builder.query({
            query: (id) => `reports/${id}`, // Raporu ID ile alma
            method: 'GET',
            providesTags:['Reports'],// Raporları önbellekleme etiketi
        }),

        getReports: builder.query({
            query: () => 'reports/getall', // Raporları alma
        method: 'GET',
        providesTags: ['Reports'],// Raporları önbellekleme etiketi

        }),
        
        getPatients: builder.query({
            query: () => 'patients', // Hastaları alma
            method: 'GET',
            providesTags: ['Patients'],
        }),
        getLaborants: builder.query({
            query: () => 'laborants/getall', // Laborantları alma
            method: 'GET',
            providesTags: ['Laborant'],
        }),
        updateReport: builder.mutation({
            query: ({id,report}) => ({
                url: `reports/edit/${id}`,
                method: 'PATCH',
                body: report,
                invalidatesTags: ['Reports'],// Yeni report eklediğinde veya güncellendiğinde 'Reports' etiketini geçersiz kılma. Böylelikle bu işlem neticesinde tekrar apiye istek atılır cachedeki veriler güncellenir.
            }),
            
            }),
        deleteLaborant: builder.mutation({
            query:(id)=>({
                url:`laborants/delete/${id}`,
                method:'DELETE',
                invalidatesTags:['Laborant'],// Silme işlemi gerçekleştiğinde 'Laborant' etiketini geçersiz kılma
    
                }),
        }),
        addLaborant: builder.mutation({
            query:(laborant)=>({
                url:'laborants/add',
                method:'POST',
                body:laborant,
                invalidatesTags:['Laborant'],// Ekleme işlemi gerçekleştiğinde 'Laborant' etiketini geçersiz kılma
    
                }),
        }),
        AddPatient: builder.mutation({
            query:(patient)=>({
                url:'patients/add',
                method:'POST',
                body:patient,
                invalidatesTags:['Patients'],// Ekleme işlemi gerçekleştiğinde 'Patients' etiketini geçersiz kılma
    
                }),
        }),
        deleteReport: builder.mutation({
            query:(id)=>({
                url:`reports/delete/${id}`,
                method:'DELETE',
                invalidatesTags:['Reports'],// Silme işlemi gerçekleştiğinde 'Reports' etiketini geçersiz kılma
    
                }),
    
            }),

        deletePatient: builder.mutation({
            query:(id)=>({
                url:`patients/delete/${id}`,
                method:'DELETE',
                invalidatesTags:['Patients'],// Silme işlemi gerçekleştiğinde 'Patients' etiketini geçersiz kılma
    
                }),
    
            }),
        }),
    });

export const {
    useGetReportByIdQuery,
    useGetPatientsQuery,
    useGetLaborantsQuery,
    useUpdateReportMutation,
    useDeleteReportMutation,
    useGetReportsQuery,
    useDeleteLaborantMutation,
    useAddLaborantMutation,
    useDeletePatientMutation,
    useAddPatientMutation,
} = apiSlice;
