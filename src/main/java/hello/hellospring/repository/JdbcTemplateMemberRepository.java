package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override public Member save(Member member) {
        // create SimpleJdbcInsert obj
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member")
                  .usingGeneratedKeyColumns("id");
        
        // data
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        
        // save to DB and return memberId
        Number memberId = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        
        // set member's id
        member.setId((Long) memberId);
        
        return member;
    }
    
    @Override public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query(
                "select * from member where id = ?",
                memberRowMapper(),
                id);
        
        return result.stream().findAny();
    }
    
    @Override public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query(
                "select * from member where name = ?",
                memberRowMapper(),
                name);
        
        return result.stream().findAny();
    }
    
    @Override public List<Member> findAll() {
        List<Member> result = jdbcTemplate.query("select * from member", memberRowMapper());
        return result;
    }
    
    // RowMapper Configuration
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
        };
    };
        
//        return new RowMapper<Member>() {
//            @Override public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return member;
//            }
//        };

}

