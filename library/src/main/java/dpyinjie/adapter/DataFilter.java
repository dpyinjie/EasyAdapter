/**
 *
 */
package dpyinjie.adapter;

/**
 * @author Created by YinJie on 2016/12/9 16:58.
 */
public interface DataFilter<D> {


    boolean accept(D data);

}
