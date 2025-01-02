import { useHistory } from 'react-router-dom';
import { useState,useEffect } from "react";
import Header from './Header';
import { useGetPatientsQuery,useDeletePatientMutation } from "./apiSlice";
import './Patient.css';



const Patient =() =>{
    const history = useHistory();
    const {data:patients=[],isLoading,isError}=useGetPatientsQuery();
    const [deletePatient]=useDeletePatientMutation();
    const [localPatients, setLocalPatients] =useState(patients);

    useEffect(() => {
        setLocalPatients(patients);
    }, [patients]);

    console.log(patients);


    if(isLoading){
        return <div>Loading...</div>;
    }
    if(isError){
        return <div>Error fetching patients</div>;
    }

    const handleDelete=async(id)=>{
        if(!window.confirm('Are you sure you want to delete this patient?')){
            return;
        }
        try{
            await deletePatient(id).unwrap();
            setLocalPatients(localPatients.filter(patient=>patient.id!==id));
            console.log(`Patient with id: ${id} deleted successfully!`);
        } catch (error) {
            console.error("Error deleting patient:", error);
        }
    };

    const handleClick = () => {
        history.push('/add-patient'); // Yönlendirme
    };

    return (
        <div className="patient-container">
            <Header />
            <h1 className="title">Laborants</h1>
            <table className="patient-table">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Patient No</th>
                        <th>Id</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {patients.map(laborant => (
                        <tr key={laborant.id}>
                            <td>{laborant.firstName}</td>
                            <td>{laborant.lastName}</td>
                            <td>{laborant.patientNo}</td>
                            <td>{laborant.id}</td>
                             <td>
                            <button className="delete-btn" onClick={() => handleDelete(laborant.id)}>Delete</button>
                        </td> 
                        </tr>
                    ))}
                                        {patients.length === 0 && (
                        <tr>
                            <td colSpan="8" style={{ textAlign: 'center' }}>No Reports Available</td>
                        </tr>
                    )}
                </tbody>
            </table>
             <button className="add-btn" onClick={handleClick}>
                Add Patient
            </button> 
        </div>
        );

}

export default Patient;