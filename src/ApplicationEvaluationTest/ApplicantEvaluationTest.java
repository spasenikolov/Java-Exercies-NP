package ApplicationEvaluationTest;

import java.util.Scanner;


/**
 * I partial exam 2016
 */
public class ApplicantEvaluationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int creditScore = scanner.nextInt();
        int employmentYears = scanner.nextInt();
        boolean hasCriminalRecord = scanner.nextBoolean();
        int choice = scanner.nextInt();
        Applicant applicant = new Applicant(name, creditScore, employmentYears, hasCriminalRecord);
        Evaluator.TYPE type = Evaluator.TYPE.values()[choice];
        Evaluator evaluator = null;
        try {
            evaluator = EvaluatorBuilder.build(type);
            System.out.println("Applicant");
            System.out.println(applicant);
            System.out.println("Evaluation type: " + type.name());
            if (evaluator.evaluate(applicant)) {
                System.out.println("Applicant is ACCEPTED");
            } else {
                System.out.println("Applicant is REJECTED");
            }
        } catch (InvalidEvaluation invalidEvaluation) {
            System.out.println("Invalid evaluation");
        }
    }
}

class Applicant {
    private String name;

    private int creditScore;
    private int employmentYears;
    private boolean hasCriminalRecord;

    public Applicant(String name, int creditScore, int employmentYears, boolean hasCriminalRecord) {
        this.name = name;
        this.creditScore = creditScore;
        this.employmentYears = employmentYears;
        this.hasCriminalRecord = hasCriminalRecord;
    }

    public String getName() {
        return name;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getEmploymentYears() {
        return employmentYears;
    }

    public boolean hasCriminalRecord() {
        return hasCriminalRecord;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nCredit score: %d\nExperience: %d\nCriminal record: %s\n",
                name, creditScore, employmentYears, hasCriminalRecord ? "Yes" : "No");
    }
}

interface Evaluator {
    enum TYPE {
        NO_CRIMINAL_RECORD,
        MORE_EXPERIENCE,
        MORE_CREDIT_SCORE,
        NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE,
        MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE,
        NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE,
        INVALID // should throw exception
    }

    boolean evaluate(Applicant applicant);
}

class EvaluatorBuilder {
    public static Evaluator build(Evaluator.TYPE type) throws InvalidEvaluation {
        CreateEvaluator createEvaluator = new CreateEvaluator();
            return createEvaluator.create(type);

    }
}

class InvalidEvaluation extends RuntimeException {

}

class CreateEvaluator {
    public CreateEvaluator() {
    }

    public Evaluator create(Evaluator.TYPE type) {
        if (type.equals(Evaluator.TYPE.NO_CRIMINAL_RECORD)) return new NoCriminalRecordEvaluationCheck();
        else if (type.equals(Evaluator.TYPE.MORE_EXPERIENCE)) return new MoreExperienceCheck();
        else if (type.equals(Evaluator.TYPE.MORE_CREDIT_SCORE)) return new MoreCreditScoreCheck();
        else if (type.equals(Evaluator.TYPE.MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE)) return new MoreExperienceAndMoreCreditScoreCheck();
        else if (type.equals(Evaluator.TYPE.NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE)) return new NoCriminalRecordAndMoreCreditScoreCheck();
        else if (type.equals(Evaluator.TYPE.NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE)) return new NoCriminalRecordAndMoreExperienceCheck();
        else throw new InvalidEvaluation();
    }
}


// имплементација на евалуатори овде
class NoCriminalRecordEvaluationCheck implements Evaluator {

    public NoCriminalRecordEvaluationCheck() {
    }

    @Override
    public boolean evaluate(Applicant applicant) {
        return !applicant.hasCriminalRecord();
    }
}

class MoreExperienceCheck implements Evaluator {
    public MoreExperienceCheck() {
    }

    @Override
    public boolean evaluate(Applicant applicant) {
        return applicant.getEmploymentYears() > 9;
    }
}

class MoreCreditScoreCheck implements Evaluator {
    @Override
    public boolean evaluate(Applicant applicant) {
        return applicant.getCreditScore() > 499;
    }
}

class NoCriminalRecordAndMoreExperienceCheck implements Evaluator {
    private final NoCriminalRecordEvaluationCheck noCriminalRecordEvaluationCheck;
    private final MoreExperienceCheck moreExperienceCheck;

    public NoCriminalRecordAndMoreExperienceCheck() {
        noCriminalRecordEvaluationCheck = new NoCriminalRecordEvaluationCheck();
        moreExperienceCheck = new MoreExperienceCheck();
    }
    @Override
    public boolean evaluate(Applicant applicant) {
        return noCriminalRecordEvaluationCheck.evaluate(applicant)&&moreExperienceCheck.evaluate(applicant);
    }
}
class NoCriminalRecordAndMoreCreditScoreCheck implements Evaluator {
    private final NoCriminalRecordEvaluationCheck noCriminalRecordEvaluationCheck;
    private final MoreCreditScoreCheck moreCreditScoreCheck;

    public NoCriminalRecordAndMoreCreditScoreCheck() {
        noCriminalRecordEvaluationCheck = new NoCriminalRecordEvaluationCheck();
        moreCreditScoreCheck = new MoreCreditScoreCheck();
    }
    @Override
    public boolean evaluate(Applicant applicant) {
        return noCriminalRecordEvaluationCheck.evaluate(applicant)&&moreCreditScoreCheck.evaluate(applicant);
    }
}
class MoreExperienceAndMoreCreditScoreCheck implements Evaluator {
    private final MoreExperienceCheck moreExperienceCheck;
    private final MoreCreditScoreCheck moreCreditScoreCheck;

    public MoreExperienceAndMoreCreditScoreCheck() {
        moreExperienceCheck = new MoreExperienceCheck();
        moreCreditScoreCheck = new MoreCreditScoreCheck();
    }
    @Override
    public boolean evaluate(Applicant applicant) {
        return moreExperienceCheck.evaluate(applicant)&&moreCreditScoreCheck.evaluate(applicant);
    }
}





