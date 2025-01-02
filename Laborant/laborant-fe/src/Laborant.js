import { useEffect } from "react";
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { useState } from "react";
import './Laborant.css';
import Header from './Header';


import { useDeleteLaborantMutation, useGetLaborantsQuery } from "./apiSlice"; // API hook'ları burada import edildiğini varsayalım


const Laborant = () => {

    const [localLaborants, setLocalLaborants] = useState([]); // Laborantları tutacak state
    const {data:laborants=[], isLoading,isError}=useGetLaborantsQuery();
    const history = useHistory(); // React Router'ın history özelliğini kullanma
    const [deleteLaborant]=useDeleteLaborantMutation();
 useEffect(() => {
        setLocalLaborants(laborants);
    }, [laborants]);

    if(isLoading){
        return <div>Loading...</div>;
    }
    if(isError){
        return <div>Error fetching laborants</div>;
    }

    const handleDelete=async(id)=>{
        if(!window.confirm('Are you sure you want to delete this laborant?')){
            return;
        }
        try{
            await deleteLaborant(id).unwrap();
            setLocalLaborants(localLaborants.filter(laborant=>laborant.id!==id));
            console.log(`Laborant with id: ${id} deleted successfully!`);
           
        } catch (error) {
            console.error("Error deleting laborant:", error);
        }
    };

    const handleClick = () => {
        history.push('/add-laborant'); // Yönlendirme
    };
    return (
        <div className="laborant-container">
            <Header />
            <h1 className="title">Laborants</h1>
            <table className="laborant-table">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Hospital identity No</th>
                        <th>Id</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {laborants.map(laborant => (
                        <tr key={laborant.id}>
                            <td>{laborant.firstName}</td>
                            <td>{laborant.lastName}</td>
                            <td>{laborant.hospitalIdentityNo}</td>
                            <td>{laborant.id}</td>
                            <td>
                            <button className="delete-btn" onClick={() => handleDelete(laborant.id)}>Delete</button>
                        </td>
                        </tr>
                    ))}
                                        {laborants.length === 0 && (
                        <tr>
                            <td colSpan="8" style={{ textAlign: 'center' }}>No Reports Available</td>
                        </tr>
                    )}
                </tbody>
            </table>
            <button className="add-btn" onClick={handleClick}>
                Add Laborant
            </button>
        </div>
        );


};






// // const Laborant = () => {
// //     const [laborants, setLaborants] = useState([]); // Laborantları tutacak state
// //     const history = useHistory(); // React Router'ın history özelliğini kullanma


// //     useEffect(() => {
// //         const fetchLaborants=async()=>{
// //             try{
// //                 const response = await axios.get('http://localhost:8080/api/laborants/getall');
// //                 setLaborants(response.data);
// //             }catch(error){
// //                 console.error("Error fetching laborants:", error);
// //             }
// //     };

// //     fetchLaborants();

// // }, []); // Boş bağımlılık dizisi ile sadece ilk render'da çalışır.

    
//     };
    
    export default Laborant;