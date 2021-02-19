# Data Migration

## Joel Fright's Data Migration Project for Sparta Global

This project was made during the 5th week of Sparta Global training. It's key functionalities include;
reading from a csv file, inserting into a database sequentially as well as concurrently via threading.

### Techniques Used

- **Connecting to a database**: This was used via JDBC, as well as through the implementation of my-sql connector
with maven.
- **Threading**: By using threading I was able to see that the insertions were much faster and as such the code was
much more efficient.
- **jUnit**: This was used to test how fast different insertions were as well as some normal unit testing.
- **Queues and HashMaps**: These were used to represent the data within the csv. HashMaps were used to
ensure the data was duplicated and Queues were used to ensure that the threads did not submit the same data twice.
- **Programming Practices**: I maintained good programming practices including SOLID and OOP throughout the project
  as well.

### Conclusions

To conclude, we can see that threading is a lot more effective in inputting data compared to sequentially. I was able
to get 5 second runtimes on a 10,000 row csv concurrently verses 30 seconds sequentially, essentially 6 times faster.

If I was to further expand upon this I would try to implement greater efficiencies within the code by using Batch 
other processes.

### Files

`src` contains all the code.

`resources` contains the csv files.