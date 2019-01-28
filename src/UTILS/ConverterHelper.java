//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ConverterHelper<E, F> {

	public abstract F transformElement(E e);

	public List<F> transform(List<E> list) {
		List<F> newList = new ArrayList<F>();
		for (E e : list) {
			newList.add(transformElement(e));
		}
		return newList;
	}

	public ObservableList<F> transform_OL(ObservableList<E> list) {
		ObservableList<F> newList = FXCollections.observableArrayList();
		for (E e : list) {
			newList.add(transformElement(e));
		}
		return newList;
	}

	public ObservableList<F> transform_list_TO_OL(List<E> list) {
		ObservableList<F> newList = FXCollections.observableArrayList();
		for (E e : list) {
			newList.add(transformElement(e));
		}
		return newList;
	}

	public List<F> transform_OL_TO_LIST(ObservableList<E> list) {
		List<F> newList = new ArrayList<F>();
		for (E e : list) {
			newList.add(transformElement(e));
		}
		return newList;
	}

	// Method that transform Integer to String
	// this override the transform method to specify the transformation

}