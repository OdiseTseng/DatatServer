package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Quiz;
import tw.intelegence.ncsist.sstp.repo.QuizRepository;

import java.util.List;

@Service("quizService")
public class QuizService {
	@Autowired
	private QuizRepository quizRepository;

	public Long getQuizId(){
		return quizRepository.findByQuizId();
	}

	public List<Quiz> getQuizList(){
		return quizRepository.findAllByUnitIdOrderByQuizId();
	}

	public List<Quiz> getQuizListByUnitId(long unitId){
		return quizRepository.findQuizsByUnitIdOrderByQuizId(unitId);
	}

	public Quiz getQuizByQuizId(long quizId){
		return quizRepository.findQuizByQuizId(quizId);
	}


	@Transactional
	public Quiz saveQuiz(Quiz quiz){
		return quizRepository.save(quiz);
	}

	@Transactional
	public List<Quiz> deleteQuiz(Quiz quiz){
		return quizRepository.delete(quiz);
	}

}
