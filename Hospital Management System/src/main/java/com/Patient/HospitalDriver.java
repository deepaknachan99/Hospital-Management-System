package com.Patient;

import java.util.Scanner;

public class HospitalDriver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("*********************WELCOME HOSPITAL MANAGEMENT SYSTEM****************");
		System.out.println();
		int option = 0;
		for (;;) {
			System.out.println("1.ADD DOCTOR");
			System.out.println("2.ADD PATIENT");
			System.out.println("3.BOOK APPOINTMENT");
			System.out.println("4.VIEW ALL DOCTOR");
			System.out.println("5.VIEW ALL PATIENT'S");
			System.out.println("6.VIEW ALL APPOINTMENTS");
			System.out.println("7.EXIT");
			System.out.println();
			System.out.print("Enter Option: ");
			option = sc.nextInt();

			switch (option) {
			case 1:
				Doctor.adddoctor();
				break;
			case 2:
				Patient.addpatient();
				break;
			case 3:
				Appointment.bookappointment();
				break;
			case 4:
				Doctor.viewDoctors();
				break;
			case 5:
				Patient.viewPatient();
				break;
			case 6:
				Appointment.viewappointment();
				break;
			case 7:
				return;
			default:
				System.out.println("INVALID OPTION ENTERED");
			}
		}
//	 
//	Patient.addpatient();
//	Doctor.viewDoctors();
//	Patient.viewPatient();
//	Appointment.bookappointment();
	}
}
