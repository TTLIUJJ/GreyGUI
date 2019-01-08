package graGUI;

import java.util.Arrays;
import java.util.List;

public class DataBag {
    private List<DataModel> dataModelList;

    private double e = 2.718281828459;
    private double [][]rawData;
    private double [][]nondimensionData;
    private double [][]index; // 分辨系数
    private double [][]ratio; // 关联系数
    private double   []degree; // 关联度

    public DataBag(List<DataModel> list) {
        this.dataModelList = list;
        initAfterOpenNewFile();
    }

    private void initAfterOpenNewFile() {
        int i = 0;
        for ( ;i < dataModelList.size(); ++i) {
            DataModel dataModel = dataModelList.get(i);
            String []points = dataModel.getPoints();
            boolean dataRow = true;
            for (int j = 0; j < points.length; ++j) {
                try {
                    double x = Double.parseDouble(points[j]);
                } catch (Exception e) {
                    dataRow = false;
                    break;
                }
            }
            if (dataRow) {
                break;
            }
        }

        int n = dataModelList.size() - i;
        int m = dataModelList.get(0).getPoints().length;
        rawData = new double[m][n];
        int beg = i;

        for ( ; i < dataModelList.size(); ++i) {
            String []points = dataModelList.get(i).getPoints();
            for (int j = 0; j < points.length; ++j) {
                rawData[j][i-beg] = Double.parseDouble(points[j]);
            }
        }

        nondimensionData = new double[m][n];
        index  = new double[m][n];
        ratio  = new double[m][n];
        degree = new double[m];
    }

    public double[][] getRawData() {
        return rawData;
    }

    public double[][] getNondimensionData() {
        return nondimensionData;
    }

    public double[][] getIndex() {
        return index;
    }

    public double[][] getRatio() {
        return ratio;
    }

    public double[] getDegree() {
        return degree;
    }


    /** ------------- 无 量 纲 化 处 理------------------- */
    public void nondimensionalization(NondimensionType type) {
        switch (type) {
            case Standardization:   standardization();   break;
            case ExtremeDifference: extremeDifference(); break;
            case Linearity:         linearity();         break;
            case Normalization:     normalization();     break;
            case Vector:            vector();            break;
        }
    }

    /** ------------- 模 型 选 择 ------------------- */
    public void modelAlgorithm(Model model) {
        switch (model) {
            case Traditional: traditional(); break;
            case General:     general();     break;
            case Dynamic:     dynamic();     break;
            case Shannon:     shannon();     break;
        }
    }

    /** 传统灰色关联算法 */
    public void traditional() {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 1; i < nondimensionData.length; ++i) {
            for (int j = 0; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                max = diff > max ? diff : max;
                min = diff < min ? diff : min;
            }
        }

        for (double[] x : index) {
            Arrays.fill(x, 0.5);
        }

        for (int i = 1; i < nondimensionData.length; ++i) {
            for (int j = 0; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                ratio[i][j] = (min + max * 0.5) / (diff + max * 0.5);
            }        }

        for (int i = 1; i < ratio.length; ++i) {
            for (int j = 0; j < ratio[i].length; ++j) {
                degree[i] += ratio[i][j];
            }
            degree[i] /= (double) ratio[i].length;
        }
    }

    /** 广义灰色关联算法 即绝对关联度 */
    public void general() {
        startValueZerolization();
        double s0 = 0;

        for (double d : nondimensionData[0]) {
            s0 += d;
        }
        s0 -= 0.5 * nondimensionData[0][nondimensionData[0].length - 1];


        for (int i = 1; i < nondimensionData.length; ++i) {
            double sI = 0;
            for (double d : nondimensionData[i]) {
                sI += d;
            }
            sI -= 0.5 * nondimensionData[i][nondimensionData[i].length-1];
            degree[i] = (1 + Math.abs(s0) + Math.abs(sI)) / (1 + Math.abs(s0) + Math.abs(sI) + Math.abs(sI - s0));
        }

    }

    /** 动态分辨系数算法 */
    public void dynamic() {
        double avg = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (int i = 0; i < nondimensionData.length; ++i) {
            for (int j = 1; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                avg += diff;
                max = diff > max ? diff : max;
                min = diff < min ? diff : min;
            }
        }

        avg /= (double)((nondimensionData.length - 1) * nondimensionData[0].length);
        double ek = avg / max;
        double _ek = 1 / ek;
        double p;
        if (_ek > 3.0) {
            p = 1.5 * ek;
        } else if (2.0 <= _ek && _ek <= 3.0) {
            p = 2.0 * ek;
        }
        else if (0 < _ek && _ek < 2){
            p = 0.9;
        }
        else {
            p = 0.5;
        }

        for (double []ds : index) {
            Arrays.fill(ds, 0.5);
        }

        for (int i = 1; i < nondimensionData.length; ++i) {
            for (int j = 0; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                ratio[i][j] = (min + p * max) / (diff + p * max);
            }
        }

        for (int i = 1; i < ratio.length; ++i) {
            for (int j = 0; j < ratio[0].length; ++j) {
                degree[i] += ratio[i][j];
            }
            degree[i] /= (double) ratio[i].length;
        }
    }

    /** 基于信息熵的算法 */
    public void shannon() {
        startValueZerolization();
        double max = Double.MIN_VALUE;

        for (int i = 0; i < nondimensionData.length; ++i) {
            for (int j = 1; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                max = diff > max ? diff : max;
            }
        }

        for (int i = 1; i < nondimensionData.length; ++i) {
            for (int j = 0; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                index[i][j] = (max * (e - 1)) / diff;
            }
        }


        for (int i = 1; i < nondimensionData.length; ++i) {
            for (int j = 0; j < nondimensionData[i].length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[0][j]);
                ratio[i][j] = (index[i][j] * max) / (diff + index[i][j] * max);
            }
        }

        for (int i = 1; i < ratio.length; ++i) {
            for (int j = 0; j < ratio[0].length; ++j) {
                degree[i] += ratio[i][j];
            }
            degree[i] /= (double) ratio[i].length;
        }
    }

    /** 初始值零化处理 */
    private void startValueZerolization() {
        for (int i = 0; i < rawData.length; ++i) {
            double startVal = rawData[i][0];
            rawData[i][0] = 0.0;
            for (int j = 1; j < rawData[i].length; ++j) {
                rawData[i][j] -= startVal;
            }
        }
    }



    /** 标准化法 */
    private void standardization() {
        for (int i = 0; i < rawData.length; ++i) {
            double avg = getAverageValue(rawData[i]);
            double std = getStandardValue(rawData[i], avg);
            for (int j = 0; j < rawData[0].length; ++j) {
                nondimensionData[i][j] = (rawData[i][j] - avg) / std;
            }
        }
    }
    /** 极差化法 */
    private void extremeDifference() {
        for (int i = 0; i < rawData.length; ++i) {
            double max = getMaxValue(rawData[i]);
            double min = getMinValue(rawData[i]);
            for (int j = 0; j < rawData[i].length; ++j) {
                nondimensionData[i][j] = (rawData[i][j] - min) / (max - min);
            }
        }
    }

    /** 最大值的线性比例法 */
    private void linearity() {
        for (int i = 0; i < rawData.length; ++i) {
            double max = getMaxValue(rawData[i]);
            for (int j = 0; j < rawData[i].length; ++j) {
                nondimensionData[i][j] = rawData[i][j] / max;
            }
        }
    }

    /** 归一化法 */
    private void normalization() {
        for (int i = 0; i < rawData.length; ++i) {
            double accumulateVal = accumulation(rawData[i]);
            for (int j = 0; j < rawData[i].length; ++j) {
                nondimensionData[i][j] = rawData[i][j] / accumulateVal;
            }
        }

    }

    /** 向量规范法 */
    private void vector() {
        for (int i = 0; i < rawData.length; ++i) {
            double temp = accumulationAndSqrt(rawData[i]);
            for (int j = 0; j < rawData[i].length; ++j) {
                nondimensionData[i][j] = rawData[i][j] / temp;
            }
        }
    }

    private double getAverageValue(double[] a) {
        double sum = 0;

        for (int i = 0; i < a.length; ++i) {
            sum += a[i];
        }

        return sum / (double)a.length;
    }

    private double getStandardValue(double []a, double avg) {
        double sum = 0;
        for (int i = 0; i  < a.length; ++i) {
            sum += Math.pow(a[i] - avg, 2);
        }

        return Math.sqrt(sum / (double)a.length);
    }

    private double getMinValue(double []a) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            min = a[i] < min ? a[i] : min;
        }

        return min;
    }

    private double getMaxValue(double []a) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            max = a[i] > max ? a[i] : max;
        }

        return max;
    }

    private double accumulation(double []a) {
        double sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += a[i];
        }

        return sum;
    }

    private double accumulationAndSqrt(double []a) {
        double sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += Math.pow(a[i], 2);
        }

        return Math.sqrt(sum);
    }
}
