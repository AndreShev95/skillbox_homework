import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FindLinks extends RecursiveTask<String> {

    private String link;
    private static String ROOT_LINK;
    private static ConcurrentSkipListSet<String> linksCommon = new ConcurrentSkipListSet<>();
    public StringBuilder stringBuilder = new StringBuilder();

    public FindLinks(String link){
        this.link = link;
    }
    public FindLinks(String link, String rootLink){
        this.link = link;
        ROOT_LINK = rootLink;
    }

    @Override
    protected String compute() {
        ConcurrentSkipListSet<String> linksLocal = new ConcurrentSkipListSet<>();
        List<FindLinks> linkList = new ArrayList<>();
        try {
            Thread.sleep(150);
            Document doc = Jsoup.connect(link).get();
            Elements elements = doc.select("a[href]");
            Set<String> absUrls = elements.stream().map(el -> el.attr("abs:href"))
                    .filter(y -> y.startsWith(ROOT_LINK))
                    .filter(v -> !v.contains("#") && !v.contains("?") && !v.contains("'") &&
                            !v.contains("&") && !v.contains("="))
                    .filter(w -> !w.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)"))
                    .filter(x -> !linksCommon.contains(x))
                    .collect(Collectors.toSet());
            absUrls.forEach(el -> {
                linksLocal.add(el);
                linksCommon.add(el);
                System.out.println(linksCommon.size());
            });
            for (String ref : linksLocal) {
                if(ref.matches(".*/")) {
                    FindLinks task = new FindLinks(ref);
                    task.fork();
                    linkList.add(task);
                }
            }
            for (FindLinks task : linkList) {
                task.join();
            }
        }
        catch (HttpStatusException httpEx){
            httpEx.getStatusCode();
            httpEx.getUrl();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        for (String link : linksCommon){
            int level = (int) link.substring(link.indexOf("//") + 2).chars().filter(ch -> ch == '/').count();
            if (link.endsWith("/")) {
                stringBuilder.append(String.join("", Collections.nCopies(level - 1, "\t")) + link + "\n");
            }
            else {
                stringBuilder.append(String.join("", Collections.nCopies(level, "\t")) + link + "/" + "\n");
            }
        }
        return stringBuilder.toString();
    }
}
