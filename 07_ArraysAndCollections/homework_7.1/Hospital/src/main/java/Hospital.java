import java.text.DecimalFormat;

public class Hospital {
    final static float MIN_TEMP = 32F;//минимальная температура пациентов
    final static float MAX_TEMP = 40F;//максимальная температура пациентов

    public static float[] generatePatientsTemperatures(int patientsCount) {

        float[] patientsTemperatures = new float[patientsCount];
        for (int i = 0; i < patientsTemperatures.length; i++) {
            patientsTemperatures[i] = (MIN_TEMP + (MAX_TEMP - MIN_TEMP) * (float) Math.random());
        }

        return patientsTemperatures;
    }


    public static String getReport(float[] temperatureData) {
        String temperatures = "";
        float averageTemp = 0F;
        int patientsHealthyCount = 0;

        for (float patientTemperature : temperatureData) {
            if (patientTemperature >= 36.2F && patientTemperature <= 36.9F) {
                ++patientsHealthyCount;
            }
            averageTemp += patientTemperature; // находим "общую" температур по больнице
            temperatures += String.format("%.1f ", patientTemperature);
        }

        DecimalFormat f2 = new DecimalFormat("#.##");
        averageTemp /= temperatureData.length; // Сумма показаний температур, деленное на их кол-во = ср. темп.

        String report =
                "Температуры пациентов: " + temperatures.trim() +
                        "\nСредняя температура: " + f2.format(averageTemp) +
                        "\nКоличество здоровых: " + patientsHealthyCount;
        return report;
    }
}
