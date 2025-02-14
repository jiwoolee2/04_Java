package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.Todo;

public class TodoListDAOImpl implements TodoListDAO{
	
	private final String FILE_PATH = "TodoList.bin";
	
	private List<Todo> todoList = null;
	
	private ObjectOutputStream oos = null;
	private ObjectInputStream	 ois = null;
	
	
	// 기본 생성자
	public TodoListDAOImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		// TodoList.bin 파일이 없으면 새로운 List 생성, 있으면 읽어오기
		File file = new File(FILE_PATH);
		
		if(!file.exists()) { // 파일이 존재하지 않는경우 새로운 리스트 만듦
			
			todoList = new ArrayList<Todo>();
			
		} else {
			// 객체 생성 시 외부 파일에 작성된 List<Todo> 객체를 입력 받아 
			// todoList에 대입
			
			// 스트림 생성
			// ObjectInputStream : 객체 입력 도와주는 보조 스트림
			ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
			
			todoList = (List<Todo>) ois.readObject();	
			
		}
		
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	/* TodoList를 파일로 저장 */
	@Override
	public void saveFile() throws FileNotFoundException, IOException {
		// 예외는 throws를 던져 버리기 때문에 catch문 불필요
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			oos.writeObject(todoList);
		}finally {
			if(oos!=null) oos.close();
		}
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	// 할 일 목록 반환 
	@Override
	public List<Todo> todoListFullView() {
		return todoList;
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	// 전달 받은 index 번째 todo를 반환, 없으면 null
	@Override
	public Todo todoDetailView(int index) {
			
		return todoList.get(index);
	}
	

	
	//-------------------------------------------------------------------------------------------------
	
	// 할 일 추가, 추가된 인덱스 번호 반환
	@Override
	public int todoAdd(Todo todo) throws FileNotFoundException, IOException {
		todoList.add(todo);
		
		/* 리시트의 마지막에 추가되기 때문에 
		 * 리스트 안에있는 개수-1 이 추가된 인덱스 번호임 */
		return todoList.size()-1;
	}

	
	//-------------------------------------------------------------------------------------------------
	
	// 할 일 완료 여부 변경 (O <-> X) 
	//  O이면 true, X면 false로 반환
	// 해당 index 요소의 완료 여부가 변경되면 true, index 요소가 없으면 false
	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {
		
		// 입력한 인덱스가 리스트의 범위를 벗어났을 때 false 반환
		if(index<0 || index>=todoList.size()) return false;

		// 입력한 인덱스가 리스트의 범위 내에 있을 때
		// todoList의 할 일 완료 여부 값(boolean)을 얻어와서 반대로 바꾸고 true 반환
		todoList.get(index).setComplete(!todoList.get(index).isComplete());
		
		return true;
	}
	
	
	
	//-------------------------------------------------------------------------------------------------
	
	// 할 일 수정
	// 성공 시 true, 실패 시 false
	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		
		// 입력한 인덱스가 리스트의 범위를 벗어났을 때 false 반환
		if(index<0 || index>=todoList.size()) return false;
		
		// 인덱스 범위 true시 title,detail값 바꾸기
		todoList.get(index).setTitle(title);
		todoList.get(index).setDetail(detail);
		return true;
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	// 할 일 삭제
	// 성공 시 삭제된 할 일(Todo) 반환,	인덱스 범위 초과로 실패 시 null 반환 
	@Override
	public Todo todoDelete(int index) throws FileNotFoundException, IOException{
		
		// 입력한 인덱스가 리스트의 범위를 벗어났을 때 false 반환
		if(index<0 || index>=todoList.size()) return null;
		
		return todoList.remove(index);
	}

}
