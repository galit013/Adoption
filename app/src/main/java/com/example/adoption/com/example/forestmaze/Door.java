package com.example.forestmaze;

public class Door {

	protected android.widget.ImageView doorImg;
	/**
	 * image of a door
	 */
	private boolean isRightDoor;
	/**
	 * bool is right door. right = true, wrong = false
	 */
	private int number;
	/**
	 * the number of the door: 1, 2 or 3
	 */
	private android.content.Context context;
	private String text_for_answer;

	/**
	 * explanation text for the answer
	 * @param doorImg
	 * @param isRightDoor
	 * @param number
	 * @param context
	 * @param text_for_answer
	 */
	public Door(android.widget.ImageView doorImg, boolean isRightDoor, int number, android.content.Context context, String text_for_answer) {
		// TODO - implement Door.Door
		throw new UnsupportedOperationException();
	}

	public android.widget.ImageView GetDoorImg() {
		// TODO - implement Door.GetDoorImg
		throw new UnsupportedOperationException();
	}

	public boolean GetIsRightDoor() {
		// TODO - implement Door.GetIsRightDoor
		throw new UnsupportedOperationException();
	}

	public int GetNumber() {
		// TODO - implement Door.GetNumber
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param imgForDoor
	 */
	public void SetDoorImg(android.widget.ImageView imgForDoor) {
		// TODO - implement Door.SetDoorImg
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param rightOrWrong
	 */
	public void SetISRightDoor(boolean rightOrWrong) {
		// TODO - implement Door.SetISRightDoor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param number
	 */
	public void SetNumber(int number) {
		// TODO - implement Door.SetNumber
		throw new UnsupportedOperationException();
	}

	public void CheckDoor() {
		// TODO - implement Door.CheckDoor
		throw new UnsupportedOperationException();
	}

}