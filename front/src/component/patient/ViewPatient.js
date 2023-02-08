import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";
import ReportsService from "../../service/ReportsService";
import '../../CSS/ViewPatient.css';


class ViewPatient extends Component {


    constructor(props) {
        super(props);
        const moment = require('moment-timezone');

        this.state = {
            id: this.props.match.params.id,
            patient: {},
            notes: [],
            noteId: '',
            patientId: '',
            patientLastName: '',
            patientFirstName: '',
            note: '',
            result: [],
            theResult: '',
            dateToStore: "26-05-2021 21:13",
            dateNote: moment().tz("Etc/Paris").format("DD/MM/YYYY HH:mm:ss"),

        }
        this.addNotes = this.addNotes.bind(this);
        this.changeNote = this.changeNote.bind(this);
    }


    componentDidMount() {
        PatientService.getPatientById(this.state.id).then(response => {
            this.setState({patient: response.data});
        })
        NotesService.getNotesById(this.state.id).then(response => {
            this.setState({notes: response.data});
        })
    }


    addNotes = (e) => {
        e.preventDefault();
        const moment2 = require('moment-timezone');
        let note = {
            
            idPatient: this.state.patient.id,
            lastName: this.state.patient.lastName,
            firstName: this.state.patient.firstName,
            note: this.state.note,
            consultationDate:  moment2().tz("Etc/Paris").format("DD/MM/YYYY HH:mm:ss"),
        }
        if (this.state.id) {
            NotesService.createNotes(note).then(response => {
                window.location.reload(true);
            });
        }

    }

    changeNote = (event) => {
        this.setState({note: event.target.value});
    }


    deleteNote(id) {
        NotesService.deleteNotes(id).then(response => {
            this.setState({notes: this.state.notes.filter(note => note.id !== id)});
            this.handleRefresh();
        })
    }

    handleRefresh = () => {
        this.setState({});
    }

    getDate = () => {
        this.setState({dateNote: new Date()})
    }

    getReports = (e) => {
        e.preventDefault();
        let resultReport;
        ReportsService.getReportsById(this.state.patient.id).then(response => {
            resultReport = response.data;
            let result = resultReport.risk;
            this.setState({theResult: result})
            this.setState({age: resultReport.age})
        })
    }


    render() {
        return (
            <div>
                <br/><br/>

                <div className="card col-md-12 container-fluid boxPatient">

                    <div className="card-body">
                        <div className="row">
                            <label> LastName : </label>
                               <div className="font-weight-bold">
                                     {this.state.patient.lastName}
                               </div>
                        </div>
                        <div className="row">
                            <label> FirstName : </label>
                               <div className="font-weight-bold">
                                 {this.state.patient.firstName}
                               </div>
                        </div>
                        <div className="row">
                            <label> Date of Birth : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.birthDate}
                            </div>
                        </div>
                        <div className="row">
                            <label>Gender : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.gender}
                            </div>
                        </div>
                        <div className="row">
                            <label>Address : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.address}
                            </div>
                        </div>
                        <div className="row">
                            <label>Phone Number : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.phoneNumber}
                            </div>
                        </div>

                        <div className="container notes">
                            <div className="table-responsive">
                                <table className="table table-striped table-bordered">
                                    <thead>
                                    <tr className="">
                                        <th className="">Note</th>
                                        <th className="">Date</th>
                                        <th className="">Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        this.state.notes.map(note =>
                                            <tr key={note.id}>
                                                <td className="note"
                                                    suppressContentEditableWarning={true} onClick={(e) => {
                                                    e.preventDefault();
                                                    window.location.href=`/add-notes/${note.id}`;
                                                }}>{note.note}</td>
                                                <td>
                                                    {note.consultationDate}
                                                </td>
                                                <td>

                                                    <button onClick={() => this.deleteNote(note.id)}
                                                            className="btn btn-sm btn-danger">X
                                                    </button>
                                                </td>
                                            </tr>
                                        )
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>


                            <div className="container">
                                <form>
                                    <div className="hide-form">
                                        <label>ID Patient</label>
                                        <input placeholder="" name="patientId" className="form-control"
                                               value={this.state.id}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>ID Notes : </label>
                                        <input placeholder="" name="idNote" className="form-control"
                                               value={this.state.note.id}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>Last Name</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.lastName}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>First Name</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.firstName}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>Date Note</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.getDate}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Notes</label>
                                        <textarea name="note" className="form-control"
                                                  value={this.state.note} onChange={this.changeNote}/>
                                    </div>
                                    <button className="btn btn-primary btn-sm" onClick={this.addNotes}>Add note
                                    </button>
                                </form>
                            </div>

                             <div className="container-fluid">
                                 <br/>
                                 <div className="row">
                                    <button className="btn btn-sm btn-success" onClick={this.getReports}>Report</button>
                                 </div>

                                    <br/>
                                    {this.state.theResult&&
                                            <div className="">
                                                <div className="row">
                                                    <label> Risk : </label>
                                                       <div className="font-weight-bold">
                                                             {this.state.theResult}
                                                       </div>
                                                </div>
                                                <div className="row">
                                                    <label> Age : </label>
                                                       <div className="font-weight-bold">
                                                             {this.state.age}
                                                       </div>
                                                </div>
                                            </div>}
                                    <br/>
                            </div>

                    </div>

                </div>
            </div>
        )
    }

}

export default ViewPatient