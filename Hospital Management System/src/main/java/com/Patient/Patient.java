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
@Table(name = "patient_info")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column
	private int age;

	private String address;

	public Patient() {
	}

	public Patient(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();

	public static void addpatient() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Patient Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Patient AGE: ");
		int age = sc.nextInt();
		System.out.print("Enter Patient ADDRESS: ");
		sc.nextLine();

		String address = sc.nextLine();

		et.begin();
		em.persist(new Patient(name, age, address));
		et.commit();
	}

	public static void viewPatient() {
		Query q = em.createNativeQuery("Select * from patient_info", Patient.class);
		List<Patient> p = q.getResultList();
		for (Patient p1 : p) {
			System.out.println("Patient ID is: " + p1.getId());
			System.out.println("Patient Name is: " + p1.getName());
			System.out.println("Patient Name is: " + p1.getAge());
			System.out.println("Patient Address is: " + p1.getAddress());
			System.out.println();
			System.out.println("--------------------------------------------------------");

		}
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}

}