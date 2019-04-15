package main;

import java.util.List;

import daoImpl.StudentsDao;
import helper.DatabaseHelper;
import model.Student;

public class Main {
	public static void main(String[] args) {
		DatabaseHelper dh = DatabaseHelper.getInstance();
		StudentsDao dao = new StudentsDao(dh);
		List<Student> studs = dao.getAll();
		
		for (Student s : studs) {
			System.out.println(s.toString());
		}
	}
}
