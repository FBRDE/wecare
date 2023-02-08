import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import '../../CSS/ListPatient.css';

class ListPatientComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            patients: []
        }
        this.addPatient = this.addPatient.bind(this);
        this.updatePatient = this.updatePatient.bind(this);
        this.deletePatient = this.deletePatient.bind(this);
        this.takeRdv = this.takeRdv.bind(this);
    }

    componentDidMount() {
        PatientService.getPatient().then((response) => {
            this.setState({patients: response.data});
        });
    }

    deletePatient(id) {
        PatientService.deletePatient(id).then (response => {
            this.setState({patients: this.state.patients.filter(patient => patient.id !== id)});
            window.location.href="/";
        });
    }

    handleRefresh = () => {
        this.setState({});
    }

    addPatient() {
        this.props.history.push('/add-patient/_add');
    }

    viewPatient(id) {
        this.props.history.push(`/view-patient/${id}`);
    }

    updatePatient(id) {
        this.props.history.push(`/add-patient/${id}`);
    }

    takeRdv() {
        this.props.history.push('/rdv');
    }

    render() {
        return (
            <div>
            <div className ="row">
                          <h2 className="">Patients</h2>

            </div>

<div className ="row">

                          <button className="btn btn-primary" onClick={this.addPatient}>Add new patient</button>
            </div>
               <br/>
                <div className ="row">
                    <table className ="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>Last Name</td>
                            <td>First Name</td>
                            <td>Birth Date</td>
                            <td>Gender</td>
                            <td>Address</td>
                            <td>Phone</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.patients.map(patient =>
                                    <tr key = {patient.id}>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.firstName}</td>
                                        <td>{patient.birthDate}</td>
                                        <td>{patient.gender}</td>
                                        <td>{patient.address}</td>
                                        <td>{patient.phoneNumber}</td>
                                        <td>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.updatePatient(patient.id)} className="btn btn-success">Update</button>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.viewPatient(patient.id)} className="btn btn-primary">View</button>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.deletePatient(patient.id)} className="btn btn-danger">Delete</button>

                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>

            </div>
        )
    }

}

export default ListPatientComponent;