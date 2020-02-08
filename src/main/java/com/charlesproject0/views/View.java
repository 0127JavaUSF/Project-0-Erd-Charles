package com.charlesproject0.views;

/**
 * Interface implemented by any class that represents a view that will display
 * a menu for inner application navigation.
 *
 */
public interface View {
	abstract void showMenu();
	View selectOption();
}
