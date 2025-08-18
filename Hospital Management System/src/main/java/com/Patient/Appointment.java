package com.Patient;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment_info")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String disease;

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	@ManyToOne
	private Patient patient;

	@ManyToOne
	private Doctor doctor;

	public Appointment() {
	}

	public Appointment(int id, String disease, Patient patient, Doctor doctor) {
		this.id = id;
		this.disease = disease;
		this.patient = patient;
		this.doctor = doctor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	public static void bookappointment() {
		Scanner sc = new Scanner(System.in);
		System.out.println("***************** Doctor INFO***************************");
		Doctor.viewDoctors();
		System.out.print("Enter id of doctor: ");
		int did = sc.nextInt();
		System.out.println("***************** Patient INFO***************************");

		Patient.viewPatient();
		System.out.print("Enter id Of patient: ");
		int pid = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Disease: ");
		String dis = sc.nextLine();
		Doctor d = em.find(Doctor.class, did);
		Patient p = em.find(Patient.class, pid);
		Appointment ap = new Appointment();
		if (d != null) {
			if (p != null) {
				ap.setDoctor(d);
				ap.setPatient(p);
				ap.setDisease(dis);
				et.begin();
				em.persist(ap);
				et.commit();
				System.out.println("Appointment Booked of Doctor " + d.getName() + " for patient " + p.getName());
			} else {
				System.out.println("Patient is not found for id: " + p.getId());
			}

		} else {
			System.out.println("Doctor is not found for id: " + d.getId());
		}
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", disease=" + disease + "\n" + ", patient=" + patient + "\n" + " doctor="
				+ doctor + "]";
	}

	public static void viewappointment() {
		Query q = em.createNativeQuery("select * from appointment_info", Appointment.class);
		List<Appointment> li = q.getResultList();
		for (Appointment ap : li) {
			System.out.println(ap);
		}
	}

}