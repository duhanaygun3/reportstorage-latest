import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AddReport.css';

const AddReport = ({ history }) => {
    // Form verileri için state tanımlıyoruz
    const [form, setForm] = useState({
        patientId: '', // Patient seçimi için id
        laborantId: '', // Laborant seçimi için id
        diagnostic: '',
        diagnosticDetail: '',
        reportDate: ''
    });

    const [patients, setPatients] = useState([]); // Patient verilerini tutacak state
    const [laborants, setLaborants] = useState([]); // Laborant verilerini tutacak state
    const [loading, setLoading] = useState(true); // API çağrısı sırasında loading durumunu yönetmek için state

    // Patient ve Laborant verilerini API'den çekiyoruz
    useEffect(() => {
        const fetchPatientsAndLaborants = async () => {
            try {
                const patientsResponse = await axios.get('/api/patients'); // Patient API endpoint'ini çağırıyoruz
                const laborantsResponse = await axios.get('/api/laborants'); // Laborant API endpoint'ini çağırıyoruz
                setPatients(patientsResponse.data);
                setLaborants(laborantsResponse.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching patients or laborants:', error);
                setLoading(false);
            }
        };

        fetchPatientsAndLaborants();
    }, []);
    

    // Input değişikliklerini yakalıyoruz
    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    // Form gönderimini işliyoruz
    const handleSubmit = async (e) => {
        e.preventDefault(); // Sayfa yenilenmesini önler

        try {
            console.log("Form Verileri: ", form); // Verileri konsola yazdırarak kontrol edin
            // Güncel API endpoint'i ile POST isteği
            await axios.post('/api/reports/add', {
                diagnostic: form.diagnostic,
                diagnosticDetail: form.diagnosticDetail,
                reportDate: form.reportDate,
                patient_id: form.patientId,  // Sadece patient_id gönderiyoruz
                laborant_id: form.laborantId // laborant_id'yi de gönderiyoruz
            });
            alert('Report added successfully!');
            history.push('/reports'); // Kullanıcıyı raporlar sayfasına yönlendir
        } catch (error) {
            console.error('Error adding report:', error);
            alert('An error occurred while adding the report.');
        }
    };

    return (
        <div className="form-container">
            <h1>Add Report</h1>
            <form onSubmit={handleSubmit}>
                {/* Patient Seçimi */}
                <label htmlFor="patientId">Select Patient</label>
                <select 
                    id="patientId" 
                    name="patientId" 
                    value={form.patientId}
                    onChange={handleChange}
                >
                    <option value="">--Select Patient--</option>
                    {
                        loading ? (
                            <option>Loading...</option> // API verileri yükleniyor ise
                        ) : (
                            patients.map(patient => (
                                <option key={patient.id} value={patient.id}>
                                    {patient.firstName} {patient.lastName}
                                </option>
                            ))
                        )
                    }
                </select>

                {/* Laborant Seçimi */}
                <label htmlFor="laborantId">Select Laborant</label>
                <select 
                    id="laborantId" 
                    name="laborantId" 
                    value={form.laborantId}
                    onChange={handleChange}
                >
                    <option value="">--Select Laborant--</option>
                    {
                        loading ? (
                            <option>Loading...</option> // API verileri yükleniyor ise
                        ) : (
                            laborants.map(laborant => (
                                <option key={laborant.id} value={laborant.id}>
                                    {laborant.firstName} {laborant.lastName}
                                </option>
                            ))
                        )
                    }
                </select>

                {/* Diğer Form Alanları */}
                <label htmlFor="diagnostic">Diagnostic</label>
                <input 
                    type="text" 
                    id="diagnostic" 
                    name="diagnostic" 
                    value={form.diagnostic}
                    onChange={handleChange}
                    placeholder="Enter diagnostic" 
                />

                <label htmlFor="diagnosticDetail">Diagnostic Detail</label>
                <textarea 
                    id="diagnosticDetail" 
                    name="diagnosticDetail" 
                    value={form.diagnosticDetail}
                    onChange={handleChange}
                    placeholder="Enter details" 
                    rows="4"
                ></textarea>

                <label htmlFor="reportDate">Report Date</label>
                <input 
                    type="date" 
                    id="reportDate" 
                    name="reportDate" 
                    value={form.reportDate} 
                    onChange={handleChange} 
                />

                <button type="submit">Submit</button>
            </form>
            <div className="form-footer">
                Please ensure all fields are filled out before submitting.
            </div>
        </div>
    );
};

export default AddReport;
