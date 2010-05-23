package examples.hibernate.dao.spring.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import examples.hibernate.dao.TagDao;
import examples.hibernate.domain.Tag;

public class TagDaoImpl extends HibernateDaoSupport implements TagDao {

	@Override
	public void addTag(Tag tag) {
		getHibernateTemplate().save(tag);
	}

	@Override
	public void deleteTag(int id) {
		Tag tag = (Tag)getHibernateTemplate().get(Tag.class, id);
		getHibernateTemplate().delete(tag);
	}

	@Override
	public Tag getTag(int id) {
		return (Tag)getHibernateTemplate().get(Tag.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tag getTag(String name) {
		Tag tag = null;
		List<Tag> tags = getHibernateTemplate().find("from Tag t where t.name = ?", name);
		if(tags!=null && tags.size()!=0){
			tag = tags.get(0);
		}
		return tag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getTags() {
		return getHibernateTemplate().find("from Tag");
	}

	@Override
	public void updateTag(Tag tag) {
		getHibernateTemplate().update(tag);
	}

}
