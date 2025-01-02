import React, { useState } from 'react';
import './AddPatient.css';
import { useHistory } from 'react-router-dom';

import { useAddPatientMutation } from './apiSlice';

const AddPatient = () => {
    const [addPatient] = useAddPatientMutation();
    const history = useHistory();

    const [form, setForm] = useState({
        firstName: '',
        lastName: '',
        patientIdNo: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Form Data:', form);
        alert('Patient successfully added!');

        try {
            const response = await addPatient(form).unwrap();
            console.log('Response:', response);
            history.push('/patients');
        } catch (error) {
            console.error('Failed to add patient:', error);
            alert('An error occurred while adding the patient.');
        }
    };

    return (
        <div className="form-container">
            <h1>Add New Patient</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="firstName">First Name</label>
                <input 
                    type="text" 
                    id="firstName" 
                    name="firstName" 
                    value={form.firstName} 
                    onChange={handleChange} 
                    placeholder="Enter patient's first name" 
                    required 
                />

                <label htmlFor="lastName">Last Name</label>
                <input 
                    type="text" 
                    id="lastName" 
                    name="lastName" 
                    value={form.lastName} 
                    onChange={handleChange} 
                    placeholder="Enter patient's last name" 
                    required 
                />

                <label htmlFor="patientIdNo">Patient ID No</label>
                <input 
                    type="text" 
                    id="patientIdNo" 
                    name="patientIdNo" 
                    value={form.patientIdNo} 
                    onChange={handleChange} 
                    placeholder="Enter patient ID number" 
                    required 
                />

                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AddPatient;
