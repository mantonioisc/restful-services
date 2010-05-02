package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.Tag;

public interface TagDao {
	public void addTag(Tag tag);
	public void updateTag(Tag tag);
	public Tag getTag(int id);
	public Tag getTag(String name);
	public List<Tag> getTags();
	public void deleteTag(int id);
}
