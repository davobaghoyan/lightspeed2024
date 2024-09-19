package org.lightspeed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Man {
	private String name;
	private int age;
	private List<String> favoriteBooks = new ArrayList<>();
	private List<Man> friends = new ArrayList<>();

	public void addFriend(Man friend) {
		friends.add(friend);
	}
}