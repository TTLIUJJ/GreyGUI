package gui.gra;

import java.util.Arrays;

public class DataBag {
    private double e = 2.718281828459;
    private double [][]rawData;
    private double [][]nondimensionData;
    private double [][]index; // 分辨系数
    private double [][]ratio; // 关联系数
    private double   []degree; // 关联度

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
        for (int i = 0; i < nondimensionData.length; ++i) {
            for (int j = 1; j < nondimensionData[i].length; ++j) {
                double val = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                max = val > max ? val : max;
                min = val < min ? val : min;
            }
        }

        Arrays.fill(index, 0.5);
        for (int i = 0; i < nondimensionData.length; ++i) {
            for (int j = 1; j < nondimensionData.length; ++j) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                ratio[i][j] = (min + max * 0.5) / (diff + max * 0.5);
            }
        }

        for (int j = 1; j < ratio[0].length; ++j) {
            for (int i = 0; i < ratio.length; ++i) {
                degree[j] += ratio[i][j];
            }
            degree[j] /= (double) ratio.length;
        }
    }

    /** 广义灰色关联算法 即绝对关联度 */
    public void general() {
        startValueZerolization();
        double s0 = 0;
        for (int i = 0; i < nondimensionData.length; ++i) {
            if (i == nondimensionData.length - 1) {
                s0 += 0.5 * nondimensionData[i][0];
            }
            else {
                s0 += nondimensionData[i][0];
            }
        }
        double sI;
        for (int j = 1; j < nondimensionData[0].length; ++j) {
            sI = 0;
            for (int i = 0; i < nondimensionData.length; ++i) {
                if (i == nondimensionData.length - 1) {
                    sI += 0.5 * nondimensionData[i][j];
                }
                else {
                    sI += nondimensionData[i][j];
                }
            }
            degree[j] = (1 + Math.abs(s0) + Math.abs(sI)) / (1 + Math.abs(s0) + Math.abs(sI) + Math.abs(sI - s0));
        }

    }

    /** 动态分辨系数算法 */
    public void dynamic() {
        double avg = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int j = 1; j < nondimensionData[0].length; ++j) {
            for (int i = 0; i < nondimensionData.length; ++i) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                avg += diff;
                max = diff > max ? diff : max;
                min = diff < min ? diff : min;
            }
        }
        avg /= (double)(nondimensionData.length * (nondimensionData[0].length - 1));
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

        for (int j = 1; j < nondimensionData[0].length; ++j) {
            for (int i = 0; i < nondimensionData.length; ++i) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                ratio[i][j] = (min + p * max) / (diff + p * max);
            }
        }

        for (int j = 1; j < ratio[0].length; ++j) {
            for (int i = 0; i < ratio.length; ++i) {
                degree[j] += ratio[i][j];
            }
            degree[j] /= (double) ratio.length;
        }
    }

    /** 基于信息熵的算法 */
    public void shannon() {
        startValueZerolization();
        double max = Double.MIN_VALUE;
        for (int i = 0; i < nondimensionData.length; ++i) {
            for (int j = 1; j < nondimensionData.length; ++j) {
                double val = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                max = val > max ? val : max;
            }
        }

        for (int i = 0; i < nondimensionData.length; ++i) {
            for (int j = 1; j < nondimensionData.length; ++j) {
                double val = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                index[i][j] = (max * (e - 1)) / val;
            }
        }

        for (int j = 1; j < nondimensionData[0].length; ++j) {
            for (int i = 1; i < nondimensionData.length; ++i) {
                double diff = Math.abs(nondimensionData[i][j] - nondimensionData[i][0]);
                ratio[i][j] = (index[i][j] * max) / (diff + index[i][j] * max);
            }
        }

        for (int j = 1; j < ratio[0].length; ++j) {
            for (int i = 0; i < ratio.length; ++i) {
                degree[j] += ratio[i][j];
            }
            degree[j] /= (double) ratio.length;
        }
    }

    /** 初始值零化处理 */
    private void startValueZerolization() {

    }



    /** 标准化法 */
    private void standardization() {
        for (int j = 0; j < rawData[0].length; ++j) {
            double avg = getAverageValue(rawData, j);
            double std = getStandardValue(rawData, avg, j);
            for (int i = 0; i < rawData.length; ++i) {
                nondimensionData[i][j] = (rawData[i][j] - avg) / std;
            }
        }
    }
    /** 极差化法 */
    private void extremeDifference() {
        for (int j = 0; j < rawData[0].length; ++j) {
            double max = getMaxValue(rawData, j);
            double min = getMinValue(rawData, j);
            for (int i = 0; i < rawData.length; ++i) {
                nondimensionData[i][j] = (rawData[i][j] - min) / (max - min);
            }
        }
    }

    /** 最大值的线性比例法 */
    private void linearity() {
        for (int j = 0; j < rawData[0].length; ++j) {
            double max = getMaxValue(rawData, j);
            for (int i = 0; i < rawData.length; ++i) {
                nondimensionData[i][j] = rawData[i][j] / max;
            }
        }
    }

    /** 归一化法 */
    private void normalization() {
        for (int j = 0; j < rawData[0].length; ++j) {
            double accumulation = accumulation(rawData, j);
            for (int i = 0; i < rawData.length; ++i) {
                nondimensionData[i][j] = rawData[i][j] / accumulation;
            }
        }
    }

    /** 向量规范法 */
    private void vector() {
        for (int j = 0; j < rawData[0].length; ++j) {
            double temp = accumulationAndSqrt(rawData, j);
            for (int i = 0; i < rawData.length; ++i) {
                nondimensionData[i][j] = rawData[i][j] / temp;
            }
        }
    }

    private double getAverageValue(double [][]a, int j) {
        double sum = 0;

        for (int i = 0; i < a.length; ++i) {
            sum += a[i][j];
        }

        return sum / (double)a.length;
    }

    private double getStandardValue(double [][]a, double avg, int j) {
        double sum = 0;
        for (int i = 0; i  < a.length; ++i) {
            sum += Math.pow(a[i][j] - avg, 2);
        }

        return Math.sqrt(sum / (double)a.length);
    }

    private double getMinValue(double [][]a, int j) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            min = a[i][j] < min ? a[i][j] : min;
        }

        return min;
    }

    private double getMaxValue(double [][]a, int j) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            max = a[i][j] > max ? a[i][j] : max;
        }

        return max;
    }

    private double accumulation(double [][]a, int j) {
        double sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += a[i][j];
        }

        return sum;
    }

    private double accumulationAndSqrt(double [][]a, int j) {
        double sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += Math.pow(a[i][j], 2);
        }

        return Math.sqrt(sum);
    }
}
