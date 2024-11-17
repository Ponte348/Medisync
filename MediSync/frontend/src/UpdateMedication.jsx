import { useEffect, useState } from 'react';
import { Modal, Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './UpdateMedication.css';

export default function UpdateMedication({ showModal, setShowModal, patient, medication=null}) {
    const addMedication = medication === null;

    const [medicationName, setMedicationName] = useState(medication===null ? '' : medication?.name);
    const [hourInterval, setHourInterval] = useState(medication===null ? '' : medication?.hourInterval);
    const [numberTimes, setNumberTimes] = useState(medication===null ? '' : medication?.numberTimes);
    const [dosage, setDosage] = useState(medication===null ? '' : medication?.dosage);

    function handleClose() {
        setShowModal(false);
    }

    const handleSave = () => {
        // Handle saving the medication information here
        console.log('Medication:', medicationName, hourInterval, numberTimes, dosage);
        handleClose();
      };

    return (
        <>
            <Modal show={showModal} onHide={handleClose} className="update-medication" centered>
                <Modal.Title className="custom-modal-title">
                    {addMedication ? 'Add' : 'Edit'} Medication Information
                </Modal.Title>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="medicationName">
                            <Form.Label>Insert the name of the medication</Form.Label>
                            <Form.Control
                            type="text"
                            placeholder="Enter medication name"
                            value={medicationName}
                            onChange={(e) => setMedicationName(e.target.value)}
                            />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="hourInterval">
                            <Form.Label>Insert the time interval in hours between administrations for this medication</Form.Label>
                            <Form.Control
                            type="number"
                            placeholder="Enter time interval"
                            value={hourInterval}
                            onChange={(e) => {if(Number.isInteger(Number(e.target.value)) && Number(e.target.value) >= 0) {setHourInterval(e.target.value)}}}
                            />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="numberTimes">
                            <Form.Label>Specify the total number of administrations for this medication</Form.Label>
                            <Form.Control
                            type="number"
                            placeholder="Enter number of administrations"
                            value={numberTimes}
                            onChange={(e) => {if(Number.isInteger(Number(e.target.value)) && Number(e.target.value) >= 0) {setNumberTimes(e.target.value)}}}
                            />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="dosage">
                            <Form.Label>Insert the dosage of the medication</Form.Label>
                            <Form.Control
                            type="text"
                            placeholder="Enter dosage"
                            value={dosage}
                            onChange={(e) => setDosage(e.target.value)}
                            />
                        </Form.Group>
                    </Form> 

                </Modal.Body>
                <Modal.Footer className="custom-modal-footer">
                    <div className="button-container">
                        <Button variant="secondary" className="cancel-button" onClick={handleClose}>
                            No, cancel
                        </Button>
                        <Button variant="primary" className="confirm-button" onClick={handleSave}>
                            Yes, confirm
                        </Button>
                    </div>
                </Modal.Footer>

            </Modal>
        </>
    );
}
