package fr.cea.ig.grools.reasoner;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * DoubleEntryTable
 */
public final class DoubleEntryTable<R, C, V> {
    
    private final Map<R,Integer> rowsHeader;
    private final Map<C,Integer> columnsHeader;
    private final List<List<V>>  values;
    
    
    public DoubleEntryTable(){
        rowsHeader      = new HashMap<>();
        columnsHeader   = new HashMap<>();
        values          = new ArrayList<>();
    }
    
    public DoubleEntryTable(@NonNull final Map<R, Integer> rowsHeader, @NonNull final Map<C, Integer> columnsHeader, @NonNull final List<List<V>> values) {
        this.rowsHeader     = rowsHeader;
        this.columnsHeader  = columnsHeader;
        this.values         = values;
    }
    
    public DoubleEntryTable(@NonNull final R[] rowsHeader, @NonNull final C[] columnsHeader, @NonNull final V[][] values) {
        this.rowsHeader     = IntStream.range(0, rowsHeader.length)
                                       .boxed()
                                       .collect( Collectors.toMap(i-> rowsHeader[i], i-> i) );
        this.columnsHeader  = IntStream.range(0, columnsHeader.length)
                                       .boxed()
                                       .collect( Collectors.toMap(i-> columnsHeader[i], i-> i) );
        this.values         = Arrays.stream(values)
                                    .map( row -> Arrays.asList(row) )
                                    .collect(Collectors.toList());
    }
    
    public boolean contains(@NonNull final R rowName, @NonNull final C columnName){
        return containsRow(rowName) && containsColumn(columnName);
    }
    
    public boolean containsRow( @NonNull final R rowName ){
        return rowsHeader.containsKey(rowName);
    }
    
    public boolean containsColumn( @NonNull final C rowName ){
        return columnsHeader.containsKey(rowName);
    }
    
    public void addRow(@NonNull final R rowHeader, @NonNull final List<V> data ) throws Exception {
        if( data.size() != columnsHeader.size() )
            throw new Exception( "Values list is not equals to number of columns");
        if( containsRow( rowHeader ) )
            throw new Exception( "Duplicate row header "+rowHeader);
        rowsHeader.put(rowHeader, rowsHeader.size());
        values.add(data);
        assert values.size() == rowsHeader.get(rowHeader);
    }
    
    public void setRow(@NonNull final R rowHeader, @NonNull final List<V> data ) throws Exception {
        if( data.size() != columnsHeader.size() )
            throw new Exception( "Values list is not equals to number of columns");
        if( ! containsRow( rowHeader ) )
            throw new Exception( "Unknown column header "+rowHeader);
        final Integer rowNum = rowsHeader.get(rowHeader);
        values.set(rowNum, data);
        assert values.size() == rowsHeader.get(rowHeader);
    }
    
    public void addColumn(@NonNull final C columnHeader, @NonNull final List<V> data ) throws Exception {
        if( data.size() != rowsHeader.size() )
            throw new Exception( "Values list is not equals to number of rows");
        if( containsColumn( columnHeader ) )
            throw new Exception( "Duplicate column header "+columnHeader);
        columnsHeader.put(columnHeader, columnsHeader.size());
        for( int rowNum = 0; rowNum < values.size(); rowNum++ )
            values.get(rowNum)
                  .add( data.get(rowNum) );
    
    }
    
    public void setColumn(@NonNull final C columnHeader, @NonNull final List<V> data ) throws Exception {
        if( data.size() != rowsHeader.size() )
            throw new Exception( "Values list is not equals to number of rows");
        if( ! containsColumn( columnHeader ) )
            throw new Exception( "Unknown column header "+columnHeader);
        final Integer colNum = columnsHeader.get(columnHeader);
         for( int rowNum = 0; rowNum < values.size(); rowNum++ )
            values.get(rowNum)
                  .set( colNum, data.get(rowNum) );
    }
    
    public void put( @NonNull final R rowHeader, @NonNull final C columnHeader, @NonNull final V value ) throws Exception {
        if( ! containsRow (rowHeader) )
            throw new Exception( "Unknown row header "+rowHeader.toString());
        if( ! containsColumn (columnHeader) )
            throw new Exception( "Unknown column header "+columnHeader.toString());
        final Integer rowNum = rowsHeader.get(rowHeader);
        final Integer colNum = columnsHeader.get(columnHeader);
        values.get(rowNum)
              .set(colNum, value);
    }
    
    public V get( @NonNull final R rowHeader, @NonNull final C columnHeader ) throws Exception {
        if (!containsRow(rowHeader))
            throw new Exception("Unknown row header " + rowHeader.toString());
        if (!containsColumn(columnHeader))
            throw new Exception("Unknown column header " + columnHeader.toString());
        final Integer rowNum = rowsHeader.get(rowHeader);
        final Integer colNum = columnsHeader.get(columnHeader);
        return values.get(rowNum)
                     .get(colNum);
    }
        
}
