package hello.hellospring.repository;

import hello.hellospring.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PostJdbcTemplateRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public PostJdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Post save(Post post) {
        // create SimpleJdbcInsert obj
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("blog")
                  .usingGeneratedKeyColumns("id");
        
        // data
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        parameters.put("createdAt", post.getCreatedAt());
        parameters.put("updatedAt", post.getUpdatedAt());
        
        // save to DB and return memberId
        Number postId = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        
        // set member's id
        post.setId((Long) postId);
        
        return post;
    }
    
    @Override
    public Optional<Post> findById(Long id) {
        List<Post> result = jdbcTemplate.query(
                "select * from blog where id = ?",
                postRowMapper(),
                id);
        
        return result.stream().findAny();
    }
    
    @Override public Optional<Post> findByName(String title) {
        List<Post> result = jdbcTemplate.query(
                "select * from blog where title = ?",
                postRowMapper(),
                title);
        
        return result.stream().findAny();
    }
    
    @Override public List<Post> findAll() {
        List<Post> result = jdbcTemplate.query("select * from blog", postRowMapper());
        return result;
    }
    
    // RowMapper Configuration
    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
                Post post = new Post();
                post.setId(rs.getLong("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                return post;
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

