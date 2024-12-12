import { Component } from 'react';
import './Report.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { withRouter } from 'react-router-dom/cjs/react-router-dom';

class Report extends Component {

    constructor() {
        super();

        this.state = {
            reports: []
        };
    }

    async componentDidMount() {
        try {
            const response = await axios.get('/api/reports/getall');
            this.setState({
                reports: response.data
            });
        } catch (error) {
            console.error("Error fetching reports:", error);
        }
    }

    handleEdit = (id) => {
        this.props.history.push(`/reports/edit/${id}`); // Edit sayfasına yönlendir
    };

    handleClick = () => {
        this.props.history.push('/add-report');  // Yönlendirme
    };

    handleDelete = async (id) => {
        
        if (!window.confirm('Are you sure you want to delete this report?')) {
            return;
        }
        try {
            await axios.delete(`/api/reports/delete/${id}`); // Raporu silme API çağrısı
            this.setState({
                reports: this.state.reports.filter(report => report.id !== id) // Silinen raporu listeden çıkar
            });
        } catch (error) {
            console.error("Error deleting report:", error);
        }
    };

    render() {
        const { reports } = this.state;

        return (
            <div className="reports-container">
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
                    {reports.map(item => (
                        <tr key={item.id}>
                            <td>{item.patientFirstName}</td>
                            <td>{item.patientLastName}</td>
                            <td>{item.laborantFirstName || 'N/A'}</td>
                            <td>{item.laborantLastName || 'N/A'}</td>
                            <td>{item.diagnostic || 'N/A'}</td>
                            <td>{item.diagnosticDetail || 'N/A'}</td>
                            <td>{item.reportDate || 'N/A'}</td>
                            <td>
                                <button className="edit-btn" onClick={() => this.handleEdit(item.id)}>Edit</button>
                                <button className="delete-btn" onClick={() => this.handleDelete(item.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                        {reports.length === 0 && (
                            <tr>
                                <td colSpan="8" style={{ textAlign: 'center' }}>No Reports Available</td>
                            </tr>
                        )}
                    </tbody>
                </table>

                <button className="add-btn" onClick={this.handleClick}>
                    Add Report
                </button>
            </div>
        );
    }
}

export default withRouter(Report);
