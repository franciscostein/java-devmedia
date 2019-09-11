package br.edu.devmedia.crud.cewolf;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.links.CategoryItemLinkGenerator;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class ViewContDados implements DatasetProducer, CategoryToolTipGenerator, CategoryItemLinkGenerator, Serializable {

    private final static String[] DIAS = {"seg", "ter", "qua", "qui", "sex", "sab"};
    private final static String[] JORNAIS = {"The Guardian", "Al Jazeera", "The Economist", "Der Spiegel"};

    @Override
    public Object produceDataset(Map<String, Object> map) throws DatasetProduceException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset() {
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                System.out.println("Finalizou");
            }
        };

        for (int i = 0; i < JORNAIS.length; i++) {

            int ultY = (int) (Math.random() * 1000 + 2000);

            for (int j = 0; j < DIAS.length; j++) {

                final int y = ultY + (int) ((Math.random() * 800 - 100));
                ultY = y;
                dataset.addValue(y, JORNAIS[i], DIAS[j]);
            }
        }

        return dataset;
    }

    @Override
    public boolean hasExpired(Map map, Date date) {
        return (System.currentTimeMillis() - date.getTime()) > 5000;
    }

    @Override
    public String getProducerId() {
        return "ABC";
    }

    @Override
    public String generateLink(Object o, int nome, Object o1) {
        return JORNAIS[nome];
    }

    @Override
    public String generateToolTip(CategoryDataset categoryDataset, int nome, int i1) {
        return JORNAIS[nome];
    }
}
