import React, { Component } from 'react';
import axios from 'axios';

class EditReport extends Component {
    constructor(props) {
        super(props);

        this.state = {
            report: {},
            patients: [],
            laborants: [],
            loading: true
        };
    }

    async componentDidMount() {
        
        const { id } = this.props.match.params;

        try {
            // Raporu, hastaları ve laborantları al
            const reportResponse = await axios.get(`/api/reports/${id}`);
            const patientsResponse = await axios.get('/api/patients');
            const laborantsResponse = await axios.get('/api/laborants'); // Laborantları al

            this.setState({
                report: reportResponse.data,
                patients: patientsResponse.data,
                laborants: laborantsResponse.data, // Laborantları state'e ekle
                loading: false
            });

            console.log(reportResponse);
        } catch (error) {
            console.error("Error fetching report, patients or lab technicians:", error);
        }
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState(prevState => ({
            report: { ...prevState.report, [name]: value }
        }));
    };

    handleSubmit = async (e) => {
        e.preventDefault();

        const { id } = this.props.match.params;
        const { diagnostic, diagnosticDetail, reportDate, patientId, laborantId } = this.state.report;

        try {
            await axios.patch('/api/reports/edit', {
                id,
                diagnostic,
                diagnosticDetail,
                reportDate,
                patientId,
                laborantId // Laborant bilgisini de ekle
            });
            alert('Report updated successfully!');
            this.props.history.push('/'); // Ana sayfaya yönlendir
        } catch (error) {
            console.error("Error updating report:", error);
            alert('Error updating report');
        }
    };

    render() {
        const { report, patients, laborants, loading } = this.state;

        return (
            <div className="form-container">
                <h1>Edit Report</h1>
                {loading ? <p>Loading...</p> : (
                    <form onSubmit={this.handleSubmit}>
                        {/* Hasta Seçimi */}
                        <label htmlFor="patientId">Select Patient</label>
                        <select
                            id="patientId"
                            name="patientId"
                            value={report.patientId || ''}  
                            onChange={this.handleChange}
                        >
                            <option value="">--Select Patient--</option>
                            {patients.map(patient => (
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
                            onChange={this.handleChange}
                        >
                            <option value="">--Select Lab Technician--</option>
                            {laborants.map(laborant => (
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
                            onChange={this.handleChange}
                            placeholder="Enter diagnostic"
                        />

                        {/* Tanı Detay Alanı */}
                        <label htmlFor="diagnosticDetail">Diagnostic Detail</label>
                        <textarea
                            id="diagnosticDetail"
                            name="diagnosticDetail"
                            value={report.diagnosticDetail || ''}
                            onChange={this.handleChange}
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
                            onChange={this.handleChange}
                        />

                        {/* Submit Button */}
                        <button type="submit">Update Report</button>
                    </form>
                )}
            </div>
        );
    }
}

export default EditReport;
