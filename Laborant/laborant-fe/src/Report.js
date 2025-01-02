import React, { useState, useEffect, use } from 'react';
import './Report.css';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import Header from './Header';

import { useGetReportsQuery } from './apiSlice';
import { useDeleteReportMutation } from './apiSlice';

const Report = () => {
    const {data:reports=[],isLoading,isError}=useGetReportsQuery();
    const [deleteReport]=useDeleteReportMutation();
    const [localReports, setLocalReports] =useState(reports);
    const history = useHistory(); // React Router'ın history özelliğini kullanma
    
    useEffect(() => {
        setLocalReports(reports);
    }, [reports]);

    if(isLoading){
        return <div>Loading...</div>;
    }
    if(isError){
        return <div>Error fetching reports</div>;
    }

        // Add Report butonu
    const handleClick = () => {
        history.push('/add-report'); // Yönlendirme
    };

        // Edit butonu
    const handleEdit = (id) => {
        history.push(`/reports/edit/${id}`); // Edit sayfasına yönlendir
    };

    const handleDelete=async(id)=>{
        if(!window.confirm('Are you sure you want to delete this report?')){
            return;
        }
        try{
            await deleteReport(id).unwrap();
            setLocalReports(localReports.filter(report=>report.id!==id));
            console.log(`Report with id: ${id} deleted successfully!`);
           
        } catch (error) {
            console.error("Error deleting report:", error);
        }
        
    }

    return (
        <div className="reports-container">
            <Header /> {/* Header bileşeni */}
            <h1 className="title">Reports</h1>

            <table className="report-table">
                <thead>
                    <tr>
                        <th>Patient First Name</th>
                        <th>Patient Last Name</th>
                        <th>Laborant First Name</th>
                        <th>Laborant Last Name</th>
                        <th>Diagnostic</th>
                        <th>Diagnostic Detail</th>
                        <th>Report Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                {localReports.map(item => (
                    <tr key={item.id}>
                        <td>{item.patientFirstName}</td>
                        <td>{item.patientLastName}</td>
                        <td>{item.laborantFirstName || 'N/A'}</td>
                        <td>{item.laborantLastName || 'N/A'}</td>
                        <td>{item.diagnostic || 'N/A'}</td>
                        <td>{item.diagnosticDetail || 'N/A'}</td>
                        <td>{item.reportDate || 'N/A'}</td>
                        <td>
                            <button className="edit-btn" onClick={() => handleEdit(item.id)}>Edit</button>
                            <button className="delete-btn" onClick={() => handleDelete(item.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                    {localReports.length === 0 && (
                        <tr>
                            <td colSpan="8" style={{ textAlign: 'center' }}>No Reports Available</td>
                        </tr>
                    )}
                </tbody>
            </table>

            <button className="add-btn" onClick={handleClick}>
                Add Report
            </button>
        </div>
    );
};



// const Report = () => {
//     const [reports, setReports] = useState([]); // State tanımlama
//     const history = useHistory(); // React Router'ın history özelliğini kullanma

//     // Component Did Mount
//     useEffect(() => {
//         const fetchReports = async () => {
//             try {
//                 const response = await axios.get('http://localhost:8080/api/reports/getall');
//                 setReports(response.data); // State'i güncelle
//                 console.log("Sa", response);
//             } catch (error) {
//                 console.error("Error fetching reports:", error);
//             }
//         };

//         fetchReports();
//     }, []); // Boş bağımlılık dizisi ile sadece ilk render'da çalışır

//     // Edit butonu
//     const handleEdit = (id) => {
//         history.push(`/reports/edit/${id}`); // Edit sayfasına yönlendir
//     };

//     // Add Report butonu
//     const handleClick = () => {
//         history.push('/add-report'); // Yönlendirme
//     };

//     // Delete butonu
//     const handleDelete = async (id) => {
//         if (!window.confirm('Are you sure you want to delete this report?')) {
//             return;
//         }
//         try {
//             await axios.delete(`http://localhost:8080/api/reports/delete/${id}`); // Raporu sil
//             setReports(reports.filter(report => report.id !== id)); // Silinen raporu listeden çıkar
//         } catch (error) {
//             console.error("Error deleting report:", error);
//         }
//     };

//     return (
//         <div className="reports-container">
//             <Header /> {/* Header bileşeni */}
//             <h1 className="title">Reports</h1>

//             <table className="report-table">
//                 <thead>
//                     <tr>
//                         <th>Patient First Name</th>
//                         <th>Patient Last Name</th>
//                         <th>Laborant First Name</th>
//                         <th>Laborant Last Name</th>
//                         <th>Diagnostic</th>
//                         <th>Diagnostic Detail</th>
//                         <th>Report Date</th>
//                         <th>Actions</th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                 {reports.map(item => (
//                     <tr key={item.id}>
//                         <td>{item.patientFirstName}</td>
//                         <td>{item.patientLastName}</td>
//                         <td>{item.laborantFirstName || 'N/A'}</td>
//                         <td>{item.laborantLastName || 'N/A'}</td>
//                         <td>{item.diagnostic || 'N/A'}</td>
//                         <td>{item.diagnosticDetail || 'N/A'}</td>
//                         <td>{item.reportDate || 'N/A'}</td>
//                         <td>
//                             <button className="edit-btn" onClick={() => handleEdit(item.id)}>Edit</button>
//                             <button className="delete-btn" onClick={() => handleDelete(item.id)}>Delete</button>
//                         </td>
//                     </tr>
//                 ))}
//                     {reports.length === 0 && (
//                         <tr>
//                             <td colSpan="8" style={{ textAlign: 'center' }}>No Reports Available</td>
//                         </tr>
//                     )}
//                 </tbody>
//             </table>

//             <button className="add-btn" onClick={handleClick}>
//                 Add Report
//             </button>
//         </div>
//     );
// };

export default Report;
