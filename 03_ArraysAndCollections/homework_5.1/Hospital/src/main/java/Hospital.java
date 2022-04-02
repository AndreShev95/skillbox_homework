import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Hospital {
    private static final float MAX_TEMPERATURE = 40;
    private static final float MIN_TEMPERATURE = 32;
    private static final float MIN_HEALTHY_TEMPERATURE = 36.2F;
    private static final float MAX_HEALTHY_TEMPERATURE = 36.9F;

    private static float sum = 0;
    private static int healthyPatients = 0;
    private static float middleTemperature;
    private static String patients = "";

    public static float[] generatePatientsTemperatures(int patientsCount) {
        //TODO: напишите метод генерации массива температур пациентов

        float[] patientsTemperatures = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++)
                {
                    patientsTemperatures[i] = MIN_TEMPERATURE + (float) (Math.random() *
                            ((MAX_TEMPERATURE - MIN_TEMPERATURE) + Float.MIN_VALUE));
                }
        return patientsTemperatures;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
        StringBuilder builder = new StringBuilder();
        builder.append(patients);

        Locale locale = new Locale("en", "UK");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols (locale);
        symbols.setDecimalSeparator ('.');

        for (float temperatures : temperatureData) {
            if (temperatures >= MIN_HEALTHY_TEMPERATURE && temperatures <= MAX_HEALTHY_TEMPERATURE) {
                healthyPatients++;
            }
            sum += temperatures;

            DecimalFormat decimalFormat = new DecimalFormat("00.0", symbols);
            String temperature = decimalFormat.format(temperatures);
            /*
            double scale = Math.pow(10, 1);
            float temperature = (float) (Math.round(temperatures * scale) / scale);
            */
            builder.append(temperature + " ");
        }
        middleTemperature = sum / temperatureData.length;

        DecimalFormat decimalFormat = new DecimalFormat("00.00", symbols);
        String middleTemp = decimalFormat.format(middleTemperature);

        /*
        double scaleMiddle = Math.pow(10, 2);
        float middleTemp = (float) (Math.round(middleTemperature * scaleMiddle) / scaleMiddle);
        */

        patients = builder.toString();

        String report =
                "Температуры пациентов: " + patients.trim() +
                        "\nСредняя температура: " + middleTemp +
                        "\nКоличество здоровых: " + healthyPatients;
        return report;
    }
}
