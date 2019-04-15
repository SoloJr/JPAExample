package daoImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import dao.Dao;
import helper.DatabaseHelper;
import model.Student;

public class StudentsDao implements Dao<Student> {
	
	private DatabaseHelper databaseHelper;
	
	public StudentsDao(DatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	@Override
	public Optional<Student> get(int id) {
		return Optional.ofNullable(databaseHelper.getEntityManager().find(Student.class, id));
	}

	@Override
	public List<Student> getAll() {
		TypedQuery<Student> query = databaseHelper.getEntityManager().createQuery("SELECT s from Student s",
				Student.class);
		return query.getResultList();
	}

	@Override
	public boolean create(Student student) {
		return databaseHelper.executeTransaction(entityManager -> entityManager.persist(student));
	}

	@Override
	public boolean update(Student old, Student newObj) {
		old.setCity(newObj.getCity());
		old.setDateOfBirth(newObj.getDateOfBirth());
		old.setFirstName(newObj.getFirstName());
		old.setLastName(newObj.getLastName());
		return databaseHelper.executeTransaction(entityManager -> entityManager.merge(old));
	}

	@Override
	public boolean delete(Student student) {
		return databaseHelper.executeTransaction(entityManager -> entityManager.remove(student));
	}

}
