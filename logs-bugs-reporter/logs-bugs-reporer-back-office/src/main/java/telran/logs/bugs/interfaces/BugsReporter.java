package telran.logs.bugs.interfaces;

import java.util.List;

import javax.validation.Valid;

import telran.logs.bugs.dto.*;

public interface BugsReporter {
	String ASSIGNMENT_DESCRIPTION_TITLE = "\nAssignment Description: ";
	ProgrammerDto addProgrammer(ProgrammerDto programmer);
	ArtifactDto addArtifact(ArtifactDto artifactDto);
	BugResponseDto openBug(BugDto bugDto);
	BugResponseDto openAndAssign(BugAssignDto bugDto);
	void assignBug(AssignBugData assignData);
	List<BugResponseDto> getNonAssignedBugs();
	void closeBug(CloseBugData closeData);
	List<BugResponseDto> getUnClosedBugMoreDuration(int days);
	List<BugResponseDto> getBugsProgrammer(long programmerId);
	List <EmailBugsCount> getEmailBugsCount();
	List<String> getProgrammersMostBugs(int nProgrammer);
	List<String> getProgrammersLeastBugs(int nProgrammer);
	BugResponseDto openAndAssignBug( BugAssignDto bugDto);

}
  