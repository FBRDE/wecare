import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";
import moment from "moment";

class CreateNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            idPatient: '',
            firstName: '',
            lastName: '',
            note: '',
            consultationDate: moment().utc().format("DD/MM/YYYY HH:mm:ss"),
            patients: []
        }
        this.changePatientIdHandler = this.changePatientIdHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeNoteHandler = this.changeNoteHandler.bind(this);
        this.changeDateNoteHandler = this.changeDateNoteHandler.bind(this);
    }

    componentDidMount() {
        if (this.state.id === '_add') {
            return
        } else {
            NotesService.getNoteById(this.state.id).then((response) => {
                let note = response.data;
                this.setState({
                    idPatient: note.idPatient,
                    firstName: note.firstName,
                    lastName: note.lastName,
                    note: note.note,
                    consultationDate: note.consultationDate,
                });
            });
            PatientService.getPatient().then((response) => {
                this.setState({patients: response.data});
            });
        }
    }

    saveOrUpdateNotes = (e) => {
        e.preventDefault();
        let note = {
            idPatient: this.state.idPatient,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            note: this.state.note,
            consultationDate: this.state.consultationDate
        };
        console.log("Note =>" + JSON.stringify(note));

        if (this.state.id === '_add') {
            NotesService.createNotes(note).then(response => {
                this.props.history.push(`/view-patient/${note.idPatient}`);
            });
        } else {
            NotesService.updateNotes(note, this.state.id).then(response => {
                this.props.history.push(`/view-patient/${note.idPatient}`);
            })
        }
    }

    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    changePatientIdHandler = (event) => {
        this.setState({patientId: event.target.value})
    }

    changeNoteHandler = (event) => {
        this.setState({note: event.target.value})
    }

    changeDateNoteHandler = (event) => {
        this.setState({dateNote: event.target.value})
    }


    cancel() {
        this.props.history.push(`/view-patient/${this.state.idPatient}`)
    }

    getTitle() {
        if(this.state.id === '_add') {
            return <h3 className="text-center">Add Notes</h3>
        } else {
            return <h3 className="text-center">Update Notes</h3>
        }
    }

    getDate = () => {
        this.setState({dateNote: new Date()})
    }

    render() {
        return (
            <div>
                <br/><br/>
                <div className = "container">
                    <div className = "row">
                        <div className ="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className = "card-body">
                                <form>
                                    <div className = "hide-form">
                                        <label>Patient ID : </label>
                                        <input placeholder="Patient ID" name="patientId" className="form-control"
                                               value={this.state.idPatient} />
                                    </div>
                                    <div className = "hide-form">
                                        <label>First Name:</label>
                                        <input placeholder="First Name" name="firstName" className="form-control"
                                               value={this.state.firstName} />
                                    </div>
                                    <div className = "hide-form">
                                        <label>Last Name:</label>
                                        <input placeholder="Last Name" name="lastName" className="form-control"
                                               value={this.state.lastName}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Note : </label>
                                        <textarea placeholder="Note" name="note" className="form-control"
                                               value={this.state.note} onChange={this.changeNoteHandler}/>
                                    </div>
                                    <div className = "hide-form">
                                        <label>Date Note : </label>
                                        <input placeholder="dd/MM/yyyy" name="dateNote" className="form-control"
                                               value={this.getDate} />
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveOrUpdateNotes}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}

export default CreateNotes;