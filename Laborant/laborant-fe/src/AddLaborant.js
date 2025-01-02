import React, { use, useState } from 'react';
import './AddLaborant.css';
import { useHistory } from 'react-router-dom';

import { useAddLaborantMutation } from './apiSlice';

const FormPage = () => {
    const[addLaborant]=useAddLaborantMutation();
    const history = useHistory();

    




    const [form, setForm] = useState({
        firstName: '',
        lastName: '',
        hospitalIdentityNo: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Form Data:', form);
        alert('Form successfully submitted!');

        const response = await addLaborant(form).unwrap();
        console.log('Response:', response);
        history.push('/laborants');

    };

    return (
        <div className="form-container">
            <h1>Input Form</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="firstName">First Name</label>
                <input 
                    type="text" 
                    id="firstName" 
                    name="firstName" 
                    value={form.firstName} 
                    onChange={handleChange} 
                    placeholder="Enter your first name" 
                />

                <label htmlFor="lastName">Last Name</label>
                <input 
                    type="text" 
                    id="lastName" 
                    name="lastName" 
                    value={form.lastName} 
                    onChange={handleChange} 
                    placeholder="Enter your last name" 
                />

                <label htmlFor="hospitalIdentityNo">Hospital Identity No</label>
                <input 
                    type="text" 
                    id="hospitalIdentityNo" 
                    name="hospitalIdentityNo" 
                    value={form.hospitalIdentityNo} 
                    onChange={handleChange} 
                    placeholder="Enter hospital identity number" 
                />

                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default FormPage;
