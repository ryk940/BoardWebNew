package com.ezen.biz.dao;

import org.springframework.stereotype.Repository;

import com.ezen.biz.common.JDBCUtil;
import com.ezen.biz.dto.BoardVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAO")
public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// SQL 명령어 상수
	private static final String BOARD_INSERT
		= "INSERT INTO board(seq, title, writer, content) VALUES(board_seq.NEXTVAL, ?, ?, ?)";
	private static final String BOARD_UPDATE
		= "UPDATE board SET title=?, writer=?, content=? WHERE seq=?";
	private static final String BOARD_DELETE
	= "DELETE FROM board WHERE seq=?";
	private static final String BOARD_GET
		= "SELECT * FROM board WHERE seq=?";
	private static final String BOARD_LIST
		= "SELECT * FROM board ORDER BY seq DESC";
	
	// 게시글 등록
	public void insertBoard(BoardVO board) {
		System.out.println("===> JDBC insertBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 글 수정
	public void updateBoard(BoardVO board) {
		System.out.println("===> JDBC updateBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getSeq());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO board) {
		System.out.println("===> JDBC deleteBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);

			pstmt.setInt(1, board.getSeq());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 글 상세 조회 - seq 번호에 의한 조회
	public BoardVO getBoard(BoardVO board) {
		System.out.println("===> JDBC getBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			
			pstmt.setInt(1, board.getSeq());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
		
		return board;
	}
	
	// 게시글 목록 조회
	public List<BoardVO> getBoardList(){
		System.out.println("===> JDBC getBoardList() 기능 처리");
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_LIST);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				
				boardList.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
		
		return boardList;
	}
}















