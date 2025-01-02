import React, { useState, useEffect } from 'react';
import { useGetReportByIdQuery, useGetPatientsQuery, useGetLaborantsQuery, useUpdateReportMutation } from './apiSlice';
import { useHistory, useParams } from 'react-router-dom';

const EditReport = () => {
    const { id } = useParams();
    const history = useHistory();

    // RTK Query hook'larını kullan
    const { data: reportData, isLoading: reportLoading } = useGetReportByIdQuery(id);
    const { data: patientsData, isLoading: patientsLoading } = useGetPatientsQuery();
    const { data: laborantsData, isLoading: laborantsLoading } = useGetLaborantsQuery();
    const [updateReport] = useUpdateReportMutation();

    const [report, setReport] = useState({});

    // Report data'yı state'e koymak için effect
    useEffect(() => {
        if (reportData) {
            setReport(reportData);
        }
    }, [reportData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setReport((prevReport) => ({
            ...prevReport,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await updateReport(report).unwrap();
            alert('Report updated successfully!');
            history.push('/');
        } catch (error) {
            console.error("Error updating report:", error);
            alert('Error updating report');
        }
    };

    if (reportLoading || patientsLoading || laborantsLoading) {
        return <p>Loading...</p>;
    }

    return (
        <div className="form-container">
            <h1>Edit Report</h1>
            <form onSubmit={handleSubmit}>
                {/* Hasta Seçimi */}
                <label htmlFor="patientId">Select Patient</label>
                <select
                    id="patientId"
                    name="patientId"
                    value={report.patientId || ''}
                    onChange={handleChange}
                >
                    <option value="">--Select Patient--</option>
                    {patientsData.map((patient) => (
                        <option key={patient.id} value={patient.id}>
                            {patient.firstName} {patient.lastName}
                        </option>
                    ))}
                </select>

                {/* Laborant Seçimi */}
                <label htmlFor="laborantId">Select Lab Technician</label>
                <select
                    id="laborantId"
                    name="laborantId"
                    value={report.laborantId || ''}
                    onChange={handleChange}
                >
                    <option value="">--Select Lab Technician--</option>
                    {laborantsData.map((laborant) => (
                        <option key={laborant.id} value={laborant.id}>
                            {laborant.firstName} {laborant.lastName}
                        </option>
                    ))}
                </select>

                {/* Tanı Alanı */}
                <label htmlFor="diagnostic">Diagnostic</label>
                <input
                    type="text"
                    id="diagnostic"
                    name="diagnostic"
                    value={report.diagnostic || ''}
                    onChange={handleChange}
                    placeholder="Enter diagnostic"
                />

                {/* Tanı Detay Alanı */}
                <label htmlFor="diagnosticDetail">Diagnostic Detail</label>
                <textarea
                    id="diagnosticDetail"
                    name="diagnosticDetail"
                    value={report.diagnosticDetail || ''}
                    onChange={handleChange}
                    placeholder="Enter details"
                    rows="4"
                ></textarea>

                {/* Rapor Tarihi */}
                <label htmlFor="reportDate">Report Date</label>
                <input
                    type="date"
                    id="reportDate"
                    name="reportDate"
                    value={report.reportDate || ''}
                    onChange={handleChange}
                />

                {/* Submit Button */}
                <button type="submit">Update Report</button>
            </form>
        </div>
    );
};

export default EditReport;
