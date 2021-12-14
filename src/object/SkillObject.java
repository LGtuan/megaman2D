package object;

public class SkillObject {
    private long timeSkill;
    private long timeStartSkill;
    private boolean isSkill;
    private boolean isFirstSkill;

    public SkillObject(long timeSkill){
        this.timeSkill = timeSkill;
        isSkill = false;
        isFirstSkill = true;
    }

    public void update(){
        if(isSkill && System.nanoTime() - timeStartSkill> timeSkill){
            isFirstSkill = true;
            isSkill = false;
        }
    }

    public long getTimeSkill() {
        return timeSkill;
    }

    public long getTimeStartSkill() {
        return timeStartSkill;
    }

    public void setTimeStartSkill(long timeStartSkill) {
        this.timeStartSkill = timeStartSkill;
    }

    public boolean getIsSkill() {
        return isSkill;
    }

    public void setIsSkill(boolean skill) {
        isSkill = skill;
    }

    public boolean getIsFirstSkill() {
        return isFirstSkill;
    }

    public void setIsFirstSkill(boolean firstSkill) {
        isFirstSkill = firstSkill;
    }
}
