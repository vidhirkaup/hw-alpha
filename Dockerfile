FROM adoptopenjdk:11-jre-hotspot
#Copy the build's output binary from the previous build container
#COPY --from=build /bin/HelloWorld /bin/HelloWorld
ENTRYPOINT ["/bin/HelloWorld"]
