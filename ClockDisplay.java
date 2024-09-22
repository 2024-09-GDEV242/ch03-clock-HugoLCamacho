
/**
 * The ClockDisplay class implements a digital clock display for an
 * American style 12 hour clock. The clock shows hours and minutes. The 
 * internal range of the clock is 01:00  to 12:59 
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Hugo Camacho
 * @version 2024.09.21
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;
    boolean am;// simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00 AM.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        hours.setValue(12);
        am=true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, boolean beforeNoon)
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        
        if(hour < 1 || hour > 12){
        hour = 12;}
        setTime(hour, minute, beforeNoon);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     * It will also check if the hours got rolled over so that it makes hours 1 instead of 0
     * It will also check if the time went to 12:00 so that it can flip the am boolean
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        
        if(hours.getValue()== 0){ hours.setValue(1);} 
        
        if(hours.getValue() == 12 && minutes.getValue() == 0 ){
        am = !am;
        
        }
        
         updateDisplay();
        
    }

    /**
     * Set the time of the display to the specified hour,
     * minute, and if the time is AM or PM
     */
    public void setTime(int hour, int minute, boolean beforeNoon)
    {
        if(hour >= 1){
        hours.setValue(hour);}
        minutes.setValue(minute);
        am = beforeNoon;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if(am){
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " AM";} else {displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " PM";
                        
                        
                        }
    }
}
