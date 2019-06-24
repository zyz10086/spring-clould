import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;

public class testToAnalysis {
    public static void main(String args[])
    {
        Forest forest = new Forest();
        try{
            forest=Library.makeForest("C:\\work\\workspace\\userLibrary2.dic");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        String word="东花市西长安街龙跃苑小区3号楼301";
//        Result parse = ToAnalysis.parse(word,forest);
        Result parse = NlpAnalysis.parse(word,forest);
        for(Term term:parse.getTerms())
        {
            System.out.println(term.getName()+"  "+term.getNatureStr());
        }
    }
}
