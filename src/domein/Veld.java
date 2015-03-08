package domein;

public class Veld {

	private int xPositie;
	private int yPositie;
	private boolean isDoel;
        
        public Veld()
        {
            
        }
        
        public Veld(boolean isDoel)
        {
            this.isDoel = isDoel;
        }
        
        public boolean isDoel()
        {
            return isDoel;
        }
        
        @Override
        public String toString()
        {
            return " ";
        }
        
}