package br.usinadigital.msgsystemws.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.usinadigital.msgsystemws.model.Message;

public class MessageRowMapper implements RowMapper<Message>
{
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setId(Integer.valueOf(rs.getString("id")));
		message.setTitle(rs.getString("title"));
		message.setText(rs.getString("text"));
		message.setCreationdate(rs.getTimestamp("creationdate"));
		message.setLastupdate(rs.getTimestamp("lastupdate"));
		message.setCategories(null);
		
		return message;
	}
}
