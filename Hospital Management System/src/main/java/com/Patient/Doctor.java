package com.Patient;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_info")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String specialization;

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialization=" + specialization + "]";
	}

	public Doctor() {
	}

	public Doctor(String name, String specialization) {
		super();
		this.name = name;
		this.specialization = specialization;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	public static void adddoctor() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Doctor Name: ");
		String Dname = sc.nextLine();
		System.out.print("Enter Doctor Speciliasation: ");
		String Dspl = sc.nextLine();
		et.begin();
		em.persist(new Doctor(Dname, Dspl));
		System.out.println("Doctor Added Succesfully");
		et.commit();
	}

	public static void viewDoctors() {
		Query q = em.createNativeQuery("Select * from doctor_info", Doctor.class);
		List<Doctor> d = q.getResultList();
		for (Doctor d1 : d) {
			System.out.println("Doctor ID is: " + d1.getId());
			System.out.println("Doctor Name is: " + d1.getName());
			System.out.println("Doctor Specillisation is: " + d1.getSpecialization());
			System.out.println("--------------------------------------------------------");

		}
	}

}